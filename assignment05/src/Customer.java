

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer {

	public static void main(String[] args) {
		ArrayList<Vehicle> list = new ArrayList<Vehicle>();

		readVehicle(list);

		Scanner input = new Scanner(System.in);
		System.out.println("please input the range of money you can spend: (least most)");
		double least = input.nextDouble();
		double most = input.nextDouble();
		System.out.println("please input how many days you want ");
		int days = input.nextInt();

		System.out.println("\nYou can rent following insuranced vehicles:");
		rentInsurancedVehicle(list,days,most,least);
		System.out.println("\nYou can rent following Motorbikes:");
		rentMotorbike(list,days,most,least);

		input.close();

	}

	public static void rentInsurancedVehicle(ArrayList<Vehicle> list, int days, double most, double least) {
		for (Vehicle v : list) {
			if (v.getRental(days) <= most && v.getRental(days) >= least) {
				if (v instanceof Insurance) {
					System.out.print(v);
					((Insurance) v).InsuanceDescription();
				}
			}
		}
	}
	public static void rentMotorbike(ArrayList<Vehicle> list, int days,double most, double least) {
		for (Vehicle v : list) {
			if (v.getRental(days) <= most && v.getRental(days) >= least) {
				if (v instanceof Motorbike) {
					System.out.println(v);
				}
			}
		}
	}

	public static void readVehicle(ArrayList<Vehicle> list) {
		try {
			Scanner scan = new Scanner(new File("vehicle.txt"));

			while (scan.hasNextLine()) {
				String str = scan.nextLine();
				String data[] = str.split(",");
				switch (data.length) {
				case 2:
					list.add(new OldVehicle(data[0], Double.parseDouble(data[1])));
					break;
				case 3:
					list.add(new Motorbike(data[0], Double.parseDouble(data[1]), Integer.parseInt(data[2])));
					break;
				case 4:
					double price = Double.parseDouble(data[1]);
					int chargeable = Integer.parseInt(data[2]);
					int oiling = Integer.parseInt(data[3]);
					list.add(new NewReleasedVehicle(data[0], price, chargeable == 1, oiling == 1));
					break;

				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}

}
