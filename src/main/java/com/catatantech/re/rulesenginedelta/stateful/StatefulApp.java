/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.catatantech.re.rulesenginedelta.stateful;

import java.util.HashMap;
import java.util.Map;
import org.drools.model.codegen.ExecutableModelProject;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

/**
 *
 * @author Admin
 */
public class StatefulApp {

	public static void main(String[] args) {
		StatefulApp app = new StatefulApp();
		app.doWork();
	}

	
	private void doWork() {
		System.out.println("hello world");
		
		KieContainer container = createKieContainer();
		KieBase base = container.getKieBase();
		KieSession session = base.newKieSession();
		
		String[] names = new String[]{"kitchen", "bedroom", "office", "livingroom"};
		Map<String, Room> name2room = new HashMap<>();
		for (String name : names) {
			Room room = new Room(name);
			name2room.put(name, room);
			session.insert(room);
			Sprinkler sprinkler = new Sprinkler(room);
			session.insert(sprinkler);
		}
		
		session.fireAllRules();
		
		Fire kitchenFire = new Fire(name2room.get("kitchen"));
		Fire officeFire = new Fire(name2room.get("office"));
		
		FactHandle fhKitchen = session.insert(kitchenFire);
		FactHandle fhOffice = session.insert(officeFire);
		
		session.fireAllRules();
		session.dispose();
	}

	
	private KieContainer createKieContainer() {
		// Programmatically collect resources and build a KieContainer
		KieServices ks = KieServices.Factory.get();
		KieFileSystem kfs = ks.newKieFileSystem();
		String packagePath = "com.catatantech.re.rulesenginedelta.stateful".replace(".", "/");
		kfs.write("src/main/resources/" + packagePath + "/rules3.drl",
				ks.getResources().newInputStreamResource(
						this.getClass().getClassLoader()
						.getResourceAsStream(packagePath + "/rules3.drl")));
		ReleaseId releaseId = ks.newReleaseId("com.catatantech.re", 
				"RulesEngineDelta", 
				"1.0-SNAPSHOT");
		kfs.generateAndWritePomXML(releaseId);
		ks.newKieBuilder(kfs).buildAll(ExecutableModelProject.class);
		return ks.newKieContainer(releaseId);
	}
}
