package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request--body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}",messageBody);

        response.getWriter().write("OK");
    }

    @PostMapping("/request--body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer outputStream) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}",messageBody);

        outputStream.write("OK");
    }

    @PostMapping("/request--body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();
        HttpHeaders headers = httpEntity.getHeaders();
        log.info("messageBody={}",messageBody);
        log.info("messageHeader={}",headers);

        return new HttpEntity<String>("ok");
    }

    @ResponseBody
    @PostMapping("/request--body-string-v4")
    public String requestBodyStringV3(@RequestBody String messaggeBody) throws IOException {

        log.info("messageBody={}",messaggeBody);

        return "ok";
    }
}
