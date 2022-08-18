package com.abc.abc.sp.testing;

import com.abc.abc.utils.QueryPS;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;


import static junit.framework.TestCase.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KaryawanJunitRestTemplateMybatis {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    QueryPS queryPS;

    @Test
    public void callAllSP() {
        // Mmebuat Prosedure lewat APlikasi spring boot
        jdbcTemplate.execute(queryPS.saveKaryawanOnlySP);
        jdbcTemplate.execute(queryPS.updateKryOnly);
        jdbcTemplate.execute(queryPS.getKryById);
        jdbcTemplate.execute(queryPS.getKryByNama);
        jdbcTemplate.execute(queryPS.deleteKryOnly);

    }

    @Test
    public void listSukses() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");

        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:9090/api/v1/sp/karyawan/list?nama=kary", HttpMethod.GET, null, String.class);
        System.out.println("response  =" + exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
    }

    @Test
    public void getIdSukses() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        Integer id = 31;
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:9090/api/v1/sp/karyawan/" + id, HttpMethod.GET, null, String.class);
        System.out.println("response  =" + exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
    }

    @Test
    public void saveSukses() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        String bodyTesting = "{\n" +
                "\"id\":\"0\",\n" +
                "\"nama\":\"Test insert karyawan\",\n" +
                "\"dob\":\"951220\",\n" +
                "\"jk\":\"Perempuan\",\n" +
                "\"alamat\":\"Jakarta\",\n" +
                "\"status\":\"Menikah\"," +
                "\"error_desc\":null, " +
                "\"error_code\":null }";
        HttpEntity<String> entity = new HttpEntity<String>(bodyTesting, headers);
        System.out.println("bodyTesting  =" + bodyTesting);
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:9090/api/v1/sp/karyawan/save", HttpMethod.POST, entity, String.class);
        System.out.println("response  =" + exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());

    }

    @Test
    public void updateSukses() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        String bodyTesting = "{\n" +
                "\"id\":\"26\",\n" +
                "\"nama\":\"Karyawan test update 24\",\n" +
                "\"dob\":\"2005-12-20\",\n" +
                "\"jk\":\"Laki-laki\",\n" +
                "\"alamat\":\"Jakarta Selatan\",\n" +
                "\"status\":\"Menikah\"\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<String>(bodyTesting, headers);

        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:9090/api/v1/sp/karyawan/update", HttpMethod.PUT, entity, String.class);

        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        System.out.println("response  =" + exchange.getBody());

    }

    @Test
    public void deleteSukses() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        Integer id = 33;

        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:9090/api/v1/sp/karyawan/delete/" + id, HttpMethod.PUT, entity, String.class);
        System.out.println("response  =" + exchange.getBody());
        Assert.assertEquals(HttpStatus.OK, exchange.getStatusCode());
    }

}
