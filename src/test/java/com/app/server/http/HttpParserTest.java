package com.app.server.http;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class HttpParserTest {

    private static HttpParser httpParser;

    @BeforeAll
    public static void beforeClass() {
        httpParser = new HttpParser();
    }

    @Test
    void parseHttpRequest() {
        HttpRequest request = null;
        try {
            request = httpParser.parseHttpRequest(
                    generateValidGETTestCase()
            );
        } catch (HttpParsingException e) {
            fail(e);
        }

        assertEquals(request.getMethod(), HttpMethod.GET);
    }
    @Test
    void parseHttpRequestBadMethod1() {
        HttpRequest request = null;
        try {
            request = httpParser.parseHttpRequest(
                    generateBadTestCaseMethodName1()
            );
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
        }
    }
    @Test
    void parseHttpRequestBadMethod2() {
        HttpRequest request = null;
        try {
            request = httpParser.parseHttpRequest(
                    generateBadTestCaseMethodName2()
            );
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
        }
    }
    @Test
    void parseHttpRequestInvNumItems() {
        HttpRequest request = null;
        try {
            request = httpParser.parseHttpRequest(
                    generateBadTestCaseRequestLineInvNumItems()
            );
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
        }
    }
    @Test
    void parseHttpEmptyRequestLine() {
        HttpRequest request = null;
        try {
            request = httpParser.parseHttpRequest(
                    generateBadTestCaseEmptyRequestLine()
            );
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
        }
    }
    @Test
    void parseHttpEmptyRequestLineOnlyCRnoLF() {
        HttpRequest request = null;
        try {
            request = httpParser.parseHttpRequest(
                    generateBadTestCaseRequestLineOnlyCRnoLF()
            );
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
        }
    }

    private InputStream generateValidGETTestCase() {
        String rowdata = "GET / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "sec-ch-ua: \"Chromium\";v=\"128\", \"Not;A=Brand\";v=\"24\", \"Microsoft Edge\";v=\"128\"\r\n" +
                "sec-ch-ua-mobile: ?0\r\n" +
                "sec-ch-ua-platform: \"Windows\"\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36 Edg/128.0.0.0\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br, zstd\r\n" +
                "Accept-Language: ru,en;q=0.9,en-GB;q=0.8,en-US;q=0.7,fr-FR;q=0.6,fr;q=0.5,ro;q=0.4\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(
                rowdata.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
          return inputStream;
    }

    private InputStream generateBadTestCaseMethodName1() {
        String rowdata = "GeT / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Accept-Language: ru,en;q=0.9,en-GB;q=0.8,en-US;q=0.7,fr-FR;q=0.6,fr;q=0.5,ro;q=0.4\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(
                rowdata.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }
    private InputStream generateBadTestCaseMethodName2() {
        String rowdata = "GETTTT / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Accept-Language: ru,en;q=0.9,en-GB;q=0.8,en-US;q=0.7,fr-FR;q=0.6,fr;q=0.5,ro;q=0.4\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(
                rowdata.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }
    private InputStream generateBadTestCaseRequestLineInvNumItems() {
        String rowdata = "GET / AAAAAAA HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Accept-Language: ru,en;q=0.9,en-GB;q=0.8,en-US;q=0.7,fr-FR;q=0.6,fr;q=0.5,ro;q=0.4\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(
                rowdata.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }
    private InputStream generateBadTestCaseEmptyRequestLine() {
        String rowdata = "\r\n" +
                "Host: localhost:8080\r\n" +
                "Accept-Language: ru,en;q=0.9,en-GB;q=0.8,en-US;q=0.7,fr-FR;q=0.6,fr;q=0.5,ro;q=0.4\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(
                rowdata.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }
    private InputStream generateBadTestCaseRequestLineOnlyCRnoLF() {
        String rowdata = "GET / AAAAAAA HTTP/1.1\r" + // <---- no LF
                "Host: localhost:8080\r\n" +
                "Accept-Language: ru,en;q=0.9,en-GB;q=0.8,en-US;q=0.7,fr-FR;q=0.6,fr;q=0.5,ro;q=0.4\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(
                rowdata.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }

}