
package com.catatantech.re.rulesenginedelta;

import com.catatantech.re.rulesenginedelta.droolsbook.Applicant;
import java.util.HashSet;
import java.util.Set;

import org.drools.model.codegen.ExecutableModelProject;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.ReleaseId;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RuleTest {
    static final Logger LOG = LoggerFactory.getLogger(RuleTest.class);

    @Test
    public void test() {
        KieContainer kContainer = createKieContainer();

        LOG.info("001 Creating kieBase");
        KieBase kieBase = kContainer.getKieBase();

        LOG.info("002 There should be rules: ");
        for ( KiePackage kp : kieBase.getKiePackages() ) {
            for (Rule rule : kp.getRules()) {
                LOG.info("002b : kp " + kp + " rule " + rule.getName());
            }
        }

        LOG.info("003 Creating kieSession");
        KieSession session = kieBase.newKieSession();

        try {
            LOG.info("004 Populating globals");
            Set<String> check = new HashSet<String>();
            session.setGlobal("controlSet", check);

            LOG.info("005 Now running data");

            Measurement mRed = new Measurement("color", "red");
            session.insert(mRed);
            session.fireAllRules();

            Measurement mGreen = new Measurement("color", "green");
            session.insert(mGreen);
            session.fireAllRules();

            Measurement mBlue = new Measurement("color", "blue");
            session.insert(mBlue);
            session.fireAllRules();

            LOG.info("006 New Rules");
            
            Applicant a = new Applicant();
            a.setName("alfa");
            a.setAge(13);
            
            Applicant b = new Applicant();
            b.setName("bravo");
            b.setAge(17);
            
            
            session.insert(a);
            session.insert(b);
            
            session.fireAllRules();
            
            LOG.info("007 END");

            assertEquals("Size of object in Working Memory is 5", 5, session.getObjects().size());
            assertTrue("contains red", check.contains("red"));
            assertTrue("contains green", check.contains("green"));
            assertTrue("contains blue", check.contains("blue"));
            
            LOG.info("008 controlset list ");
            for (String c : check) {
                System.out.println(c);
            }
        } finally {
            session.dispose();
        }
    }

    /*
     * 01. create KieServices
     * 02. baca rule dari sumber-sumber rule
     * 03. ks membaca rule, dan menghasilkan kieContainer
     * 04. create kieSession
     * 05. masukkan object yg diukur satu demi satu, dan jalankan rulesnya terhadap measurement tersebut
     */
    
    
    
    private KieContainer createKieContainer() {
        // Programmatically collect resources and build a KieContainer
        KieServices ks = KieServices.Factory.get();
        KieFileSystem kfs = ks.newKieFileSystem();
        String packagePath = "com.catatantech.re.rulesenginedelta".replace(".", "/");
        kfs.write("src/main/resources/" + packagePath + "/rules.drl",
                  ks.getResources().newInputStreamResource(this.getClass().getClassLoader().getResourceAsStream(packagePath + "/rules.drl")));
        ReleaseId releaseId = ks.newReleaseId("com.catatantech.re", "RulesEngineDelta", "1.0-SNAPSHOT");
        kfs.generateAndWritePomXML(releaseId);
        ks.newKieBuilder(kfs).buildAll(ExecutableModelProject.class);
        return ks.newKieContainer(releaseId);
    }
}