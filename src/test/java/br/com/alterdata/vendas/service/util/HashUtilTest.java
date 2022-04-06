package br.com.alterdata.vendas.service.util;

import br.com.alterdata.vendas.Util.HashUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class HashUtilTest {

    @Test
    public void getSecureHashTest(){
        String hash = HashUtil.getSecureHash("123456");
        System.out.println(hash);

        assertThat(hash.length()).isEqualTo(64);
    }
}
