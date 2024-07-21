package dig.into.spring.core.example;

import java.util.List;

interface RecipeInjection {
	void inject(Bread bread, Cheese cheese, List<Sauce> sauce);
}