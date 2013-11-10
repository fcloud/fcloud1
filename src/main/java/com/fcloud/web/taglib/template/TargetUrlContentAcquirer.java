package com.fcloud.web.taglib.template;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import java.io.*;
import java.util.Locale;

/**
 * 远程数据获取类
 * @author Ruben Fu
 */
public class TargetUrlContentAcquirer {

    public static final String DEFAULT_ENCODING = "UTF-8";

    private String charEncoding;

    private PageContext pageContext;

    private String targetUrl;

    public TargetUrlContentAcquirer(String targetUrl, PageContext pageContext, String charEncoding) {
        this.targetUrl = targetUrl;
        this.pageContext = pageContext;
        this.charEncoding = charEncoding;
    }

    public String acquireString() throws IOException, JspException {
        ServletContext c = pageContext.getServletContext();

        RequestDispatcher rd = c.getRequestDispatcher(targetUrl);

        // include the resource, using our custom wrapper
        ImportResponseWrapper irw = new ImportResponseWrapper(pageContext);

        // spec mandates specific error handling form include()
        try {
            rd.include(pageContext.getRequest(), irw);
        } catch (IOException ex) {
            throw new JspException(ex);
        } catch (RuntimeException ex) {
            throw new JspException(ex);
        } catch (ServletException ex) {
            Throwable rc = ex.getRootCause();
            if (rc == null)
                throw new JspException(ex);
            else
                throw new JspException(rc);
        }

        // disallow inappropriate response codes per JSTL spec
        if (irw.getStatus() < 200 || irw.getStatus() > 299) {
            throw new JspTagException(irw.getStatus() + " " + targetUrl);
        }

        // recover the response String from our wrapper
        return irw.getString();
    }

    // // ==================== 来至 ImportSupport 标签内容

    /** Wraps responses to allow us to retrieve results as Strings. */
    protected class ImportResponseWrapper extends HttpServletResponseWrapper {

        // ************************************************************
        // Overview

		/*
		 * We provide either a Writer or an OutputStream as requested. We
		 * actually have a true Writer and an OutputStream backing both, since
		 * we don't want to use a character encoding both ways (Writer ->
		 * OutputStream -> Writer). So we use no encoding at all (as none is
		 * relevant) when the target resource uses a Writer. And we decode the
		 * OutputStream's bytes using OUR tag's 'charEncoding' attribute, or
		 * ISO-8859-1 as the default. We thus ignore setLocale() and
		 * setContentType() in this wrapper.
		 *
		 * In other words, the target's asserted encoding is used to convert
		 * from a Writer to an OutputStream, which is typically the medium
		 * through with the target will communicate its ultimate response. Since
		 * we short-circuit that mechanism and read the target's characters
		 * directly if they're offered as such, we simply ignore the target's
		 * encoding assertion.
		 */

        // ************************************************************
        // Data
        /** The Writer we convey. */
        private StringWriter sw = new StringWriter();

        /** A buffer, alternatively, to accumulate bytes. */
        private ByteArrayOutputStream bos = new ByteArrayOutputStream();

        /** A ServletOutputStream we convey, tied to this Writer. */
        private ServletOutputStream sos = new ServletOutputStream() {
            public void write(int b) throws IOException {
                bos.write(b);
            }
        };

        /** 'True' if getWriter() was called; false otherwise. */
        private boolean isWriterUsed;

        /** 'True if getOutputStream() was called; false otherwise. */
        private boolean isStreamUsed;

        /** The HTTP status set by the target. */
        private int status = 200;

        private PageContext pageContext;

        // ************************************************************
        // Constructor and methods

        /** Constructs a new ImportResponseWrapper. */
        public ImportResponseWrapper(PageContext pageContext) {
            super((HttpServletResponse) pageContext.getResponse());
            this.pageContext = pageContext;
        }

        /** Returns a Writer designed to buffer the output. */
        public PrintWriter getWriter() throws IOException {
            if (isStreamUsed)
                throw new IllegalStateException("已经使用Stream作为输出方式!");
            isWriterUsed = true;
            return new PrintWriterWrapper(sw, pageContext.getOut());
        }

        /** Returns a ServletOutputStream designed to buffer the output. */
        public ServletOutputStream getOutputStream() {
            if (isWriterUsed)
                throw new IllegalStateException("已经使用Writer作为输出方式!");
            isStreamUsed = true;
            return sos;
        }

        /** Has no effect. */
        public void setContentType(String x) {
            // ignore
        }

        /** Has no effect. */
        public void setLocale(Locale x) {
            // ignore
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }

        /**
         * Retrieves the buffered output, using the containing tag's
         * 'charEncoding' attribute, or the tag's default encoding, <b>if
         * necessary</b>.
         */
        // not simply toString() because we need to throw
        // UnsupportedEncodingException
        public String getString() throws UnsupportedEncodingException {
            if (isWriterUsed)
                return sw.toString();
            else if (isStreamUsed) {
                if (charEncoding != null && !charEncoding.equals(""))
                    return bos.toString(charEncoding);
                else
                    return bos.toString(DEFAULT_ENCODING);
            } else
                return ""; // target didn't write anything
        }
    }

    protected static class PrintWriterWrapper extends PrintWriter {

        private StringWriter out;
        private Writer parentWriter;

        public PrintWriterWrapper(StringWriter out, Writer parentWriter) {
            super(out);
            this.out = out;
            this.parentWriter = parentWriter;
        }

        public void flush() {
            try {
                parentWriter.write(out.toString());
                StringBuffer sb = out.getBuffer();
                sb.delete(0, sb.length());
            } catch (IOException ex) {
            }
        }
    }
}