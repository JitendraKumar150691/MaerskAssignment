import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Promotion {

	static Map<String, String> promotions = new HashMap<>();
	static Map<String, Integer> items = new HashMap<>();

	public static void main(String[] args) {

		addItems();
		addPromotion();
		System.out.println(checkOut(Arrays.asList("A", "B", "C"), Arrays.asList(1, 1, 1)));
		System.out.println(checkOut(Arrays.asList("A", "B", "C"), Arrays.asList(5, 5, 1)));
		System.out.println(checkOut(Arrays.asList("A", "B", "C", "D"), Arrays.asList(3, 5, 1, 1)));
		System.out.println(checkOut(Arrays.asList("A", "B", "C", "D", "E"), Arrays.asList(3, 5, 1, 2, 1)));
	}

	public static void addPromotion() {

		Scanner scan = new Scanner(System.in);
		System.out.print("How many prootions ?");
		int no = scan.nextInt();

		while (no > 0) {
			System.out.print("Please enter promotions and its values");
			String exp = scan.next();
			String value = scan.next();

			promotions.put(exp, value);
			no--;
		}
	}
	
	public static void addItems() {
		
		Scanner scan = new Scanner(System.in);
		System.out.print("How many Items ?");
		int no = scan.nextInt();

		while (no > 0) {
			System.out.print("Please enter Item SKU (A to Z) and its price");
			String itemSKU = scan.next();
			int price = scan.nextInt();

			items.put(itemSKU, price);
			no--;
		}
	}
	
	public static float checkOut (List<String> itemSKUs, List<Integer> quantities) {
		
		float result = 0;
		
		for(int i = 0; i < itemSKUs.size(); i++) {
			
			String sku = itemSKUs.get(i);
			int  skuQuantity = quantities.get(i);
			
			float skuPrice = items.get(sku);
			
			if (skuQuantity >= 1) {
				
				float skuTotPrice = 0; 
				boolean promotionApplied = false;
				
				for (int k = 5; k >= 2; k--) {
					
					int pair = (skuQuantity / k);
					int remainQ = (skuQuantity % k);
					if (pair >= 1 && promotions.containsKey(k+sku)) {

						String pairPrice = promotions.get(k+sku);
						float pairPriceAct = 0;
						
						if (pairPrice.contains("%")) {
							pairPrice = pairPrice.replace("%", "");
							pairPriceAct = Float.parseFloat(pairPrice);
							pairPriceAct = pairPriceAct/100 * skuPrice*k;
						} else {
							pairPriceAct = Float.parseFloat(promotions.get(k+sku));
						}
								
						skuTotPrice = pairPriceAct * pair;
						skuTotPrice += remainQ * skuPrice;
						result += skuTotPrice;
						promotionApplied =true;
						break;
					}
				}
				System.out.println(sku+ "" +  skuTotPrice);
				if(promotionApplied)
					continue;
				
				for (int j = i+1; j < itemSKUs.size(); j++) {
					
					String combbination = sku + "+" + itemSKUs.get(j);
					
					if (!promotions.containsKey(combbination))
						continue;
					
					int combiQuantity = quantities.get(j);
					float combiPrice = Float.parseFloat(promotions.get(combbination));
					
					if (skuQuantity >= combiQuantity) {
						
						int remainQ = (skuQuantity- combiQuantity);
						quantities.set(j,0);
						
						skuTotPrice = (combiQuantity * combiPrice) + (remainQ * skuPrice);
						
					} else if(combiQuantity > skuQuantity) {
						
						int remainQ = (combiQuantity - skuQuantity);
						quantities.set(j,remainQ);
						skuTotPrice = (skuQuantity * combiPrice);
					}
					
					result+=skuTotPrice;
					System.out.println(sku+ "" +  skuTotPrice);
					promotionApplied = true;
				}
				
				if(!promotionApplied) {
					result+=skuPrice;
					continue;
				}
			}
		}
		
		return result;
	}

}
