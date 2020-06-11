package br.gabriel.springrestspecialist.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.gabriel.springrestspecialist.SpringRestSpecialistApplication;
import br.gabriel.springrestspecialist.domain.model.Kitchen;
import br.gabriel.springrestspecialist.domain.repository.KitchenRepository;

public class KitchenQueryMain {
	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = new SpringApplicationBuilder(SpringRestSpecialistApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
		
		KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);
		
		Kitchen k1 = new Kitchen();
		k1.setName("Brasilian");
		
		Kitchen k2 = new Kitchen();
		k2.setName("Vietnamese");
		
		kitchenRepository.save(k1);
		kitchenRepository.save(k2);
		
		List<Kitchen> kitchens = kitchenRepository.findAll();
		
		for (Kitchen kitchen : kitchens) {
			System.out.println(kitchen.getName());
		}
		
		Kitchen kitchen = new Kitchen();
		kitchen.setId(1);
		kitchen.setName("Russian");
		
		kitchenRepository.save(kitchen);
		
		System.out.println(kitchen.getName());
		
		kitchenRepository.delete(kitchen);
		
		for (Kitchen k : kitchens) {
			System.out.println(k.getName());
		}
	}
}
