package diarsid.librarian.impl.logic.impl.search.v2;


import diarsid.librarian.impl.logic.impl.search.charscan.CountCharMatches;
import org.junit.jupiter.api.Test;

import static diarsid.librarian.impl.logic.impl.search.charscan.CharSort.transform;
import static org.junit.jupiter.api.Assertions.fail;

public class LengthTest {


    public void testLength(String pattern, String word, boolean expectMatching) {
        int rate = CountCharMatches.CURRENT_VERSION.evaluate(transform(pattern), transform(word), 60);
        if ( expectMatching ) {
            if ( rate < 0 ) {
                fail();
            }
        }
        else {
            if ( rate > -1 ) {
                fail();
            }
        }
    }

    @Test
    public void test_tolknebyjr_jrr() {
        testLength("tolknebyjr", "jrr", true);
    }

    @Test
    public void test_srvr_servers() {
        testLength("srvr", "servers", true);
    }

    @Test
    public void test_srvrs_servers() {
        testLength("srvrs", "servers", true);
    }

    @Test
    public void test_srv_servers() {
        testLength("srv", "servers", true);
    }

    @Test
    public void test_srvcs_services() {
        testLength("srvcs", "services", true);
    }

    @Test
    public void test_prj_projects() {
        testLength("prj", "projects", true);
    }

    @Test
    public void test_porj_projects() {
        testLength("porj", "projects", true);
    }

    @Test
    public void test_3toolsserv_servers() {
        testLength("3toolsserv", "servers", true);
    }

    @Test
    public void test_toolssevrirtl_tools() {
        testLength("toolssevrirtl", "tools", true);
    }

    @Test
    public void test_toolssevrirtl_servers() {
        testLength("toolssevrirtl", "servers", true);
    }

    @Test
    public void test_toolssevrirtl_virtualization() {
        testLength("toolssevrirtl", "virtualization", true);
    }

    @Test
    public void test_3toolssevrirtl_virtualization() {
        testLength("3toolssevrirtl", "virtualization", true);
    }

    @Test
    public void test_roedck_docker() {
        testLength("roedck", "docker", true);
    }

    @Test
    public void test_docekr_docker() {
        testLength("docekr", "docker", true);
    }

    @Test
    public void test_dckre_docker() {
        testLength("dckre", "docker", true);
    }

    @Test
    public void test_lorofrngbyjrrtolk_lord() {
        testLength("lorofrngbyjrrtolk", "lord", true);
    }

    @Test
    public void test_lorofrngbyjrrtolk_of() {
        testLength("lorofrngbyjrrtolk", "of", true);
    }

    @Test
    public void test_lorofrngbyjrrtolk_rings() {
        testLength("lorofrngbyjrrtolk", "rings", true);
    }

    @Test
    public void test_lorofrngbyjrrtolk_by() {
        testLength("lorofrngbyjrrtolk", "by", true);
    }

    @Test
    public void test_lorofrngbyjrrtolk_jrr() {
        testLength("lorofrngbyjrrtolk", "jrr", true);
    }

    @Test
    public void test_lorofrngbyjrrtolk_tolkien() {
        testLength("lorofrngbyjrrtolk", "tolkien", true);
    }

    @Test
    public void test_byjrrtolk_tolkien() {
        testLength("byjrrtolk", "tolkien", true);
    }

    @Test
    public void test_lorofrngbyjrrtolk_tolkiens() {
        testLength("lorofrngbyjrrtolk", "tolkiens", true);
    }

    @Test
    public void test_lorofrngbyjrrtolk_tolkien1s() {
        testLength("lorofrngbyjrrtolk", "tolkien1s", true);
    }

    @Test
    public void test_wh_warhammer() {
        testLength("wh", "warhammer", true);
    }

    @Test
    public void test_romerise_rising() {
        testLength("romerise", "rising", true);
    }

    @Test
    public void test_yasnrkawbata_yasunari() {
        testLength("yasnrkawbata", "yasunari", true);
    }

    @Test
    public void test_yasnrkawbata_kawabata() {
        testLength("yasnrkawbata", "kawabata", true);
    }

    @Test
    public void test_yasnkawbata_yasunari() {
        testLength("yasnkawbata", "yasunari", true);
    }

    @Test
    public void test_yasnrkwabta_kawabata() {
        testLength("yasnrkwabta", "kawabata", true);
    }

    @Test
    public void test_yasnrkwabta_yasunari() {
        testLength("yasnrkwabta", "yasunari", true);
    }
}
