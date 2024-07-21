package dig.into.spring.core;

import java.util.List;

import dig.into.spring.core.example.Bread;
import dig.into.spring.core.example.Cheese;
import dig.into.spring.core.example.ChiliSauce;
import dig.into.spring.core.example.MozzarellaCheese;
import dig.into.spring.core.example.Sauce;
import dig.into.spring.core.example.WhiteBread;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DI {

	public static void main(String[] args) {
		
		Bread whiteBread = new WhiteBread();
		Cheese mozzarellaCheese = new MozzarellaCheese();
		Sauce chilisauce = new ChiliSauce();
		
		List<Sauce> sauceList = chilisauce.setSauceList(chilisauce);
		
		ItalianBMTDI bmt = new ItalianBMTDI(whiteBread, mozzarellaCheese, sauceList);
		
		System.out.println("bmt재료::"+ bmt.logForItalianBMTDI());

	}
	
	// Not DI 
	public static class ItalianBMT {
		WhiteBread whiteBread;
		MozzarellaCheese mozzarellaCheese;
		ChiliSauce chiliSauce;
		
		public ItalianBMT() {
			this.whiteBread = new WhiteBread();
			this.mozzarellaCheese = new MozzarellaCheese();
			this.chiliSauce = new ChiliSauce();
		}
	}
	
	
	// IoC Principle을 지키기 위한 DI(Dependency Injection)적용
	public static class ItalianBMTDI implements RecipeInjection {
		Bread bread;
		Cheese cheese;
		List<Sauce> sauce;
		
		// 1. DI를 위한 주입방법 : 생성자 주입
		public ItalianBMTDI(Bread bread, Cheese cheese, List<Sauce> sauce) {
			this.bread = bread;
			this.cheese = cheese;
			this.sauce = sauce;
		}
		
		// 2. DI를 위한 주입방법 : setter 주입
		/*
		public void setBread(Bread bread) {
			this.bread = bread;
		}
		public void setCheese(Cheese cheese) {
			this.cheese = cheese;
		}
		public void setSauce(List<Sauce> sauce) {
			this.sauce = sauce;
		}
		*/

		// 3. DI를 위한 주입방법 : 메서드 주입
		@Override
		public void inject(Bread bread, Cheese cheese, List<Sauce> sauce) {
			this.bread = bread;
			this.cheese = cheese;
			this.sauce = sauce;
		}
		
		

		public String logForItalianBMTDI() {
			System.out.println("ItalianBMTDI 빵::" + this.bread);
			System.out.println("ItalianBMTDI 치즈::" + this.cheese);
			System.out.println("ItalianBMTDI 소스::" + this.sauce);
			return "";
		}
		
	}
	
	interface RecipeInjection {
		void inject(Bread bread, Cheese cheese, List<Sauce> sauce);
	}

}
