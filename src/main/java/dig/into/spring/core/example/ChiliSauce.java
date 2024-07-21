package dig.into.spring.core.example;

import java.util.ArrayList;
import java.util.List;

public class ChiliSauce implements Sauce {

	@Override
	public String getSauceType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Sauce> setSauceList(Sauce addedSauce){
		List<Sauce> sauce = new ArrayList<>();
		sauce.add(addedSauce);
		return sauce;
	}

}
