package br.gabriel.springrestspecialist.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.gabriel.springrestspecialist.SpringRestSpecialistApplication;
import br.gabriel.springrestspecialist.domain.model.Kitchen;

public class KitchenQueryMain {
	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = new SpringApplicationBuilder(SpringRestSpecialistApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
		
		KitchenRegistration kitchenRegistration = applicationContext.getBean(KitchenRegistration.class);
		
		Kitchen k1 = new Kitchen();
		k1.setName("Brasilian");
		
		Kitchen k2 = new Kitchen();
		k2.setName("Vietnamese");
		
		kitchenRegistration.save(k1);
		kitchenRegistration.save(k2);
		
		List<Kitchen> kitchens = kitchenRegistration.findAll();
		
		for (Kitchen kitchen : kitchens) {
			System.out.println(kitchen.getName());
		}
		
		Kitchen kitchen = new Kitchen();
		kitchen.setId(1);
		kitchen.setName("Russian");
		
		kitchenRegistration.save(kitchen);
		
		System.out.println(kitchen.getName());
		
		kitchenRegistration.remove(kitchen);
		
		for (Kitchen k : kitchens) {
			System.out.println(k.getName());
		}
	}
}
