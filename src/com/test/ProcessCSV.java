package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/data")
public class ProcessCSV {
	
	@GET
	@Path("/items")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createJSONObject() {
		List<DataItem> data = new ArrayList<DataItem>();
		try {			
			File file = new File("C:/kamal/INTERFACES_TASK_INPUT.csv");
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = bf.readLine();
			String[] lineItems = line.split(",");
			//System.out.println(lineItems.length);
			System.out.println(line);
			int minFields = 4;
			List<Integer> sizes = new ArrayList<Integer>();
			if (lineItems.length > minFields) {
				for (int i = 2; i < lineItems.length - 2; i++) {
					sizes.add(Integer.parseInt(lineItems[i]));
				}
			}
			while ((line = bf.readLine()) != null) {
				System.out.println(line);
				lineItems=line.split(",");
				if (lineItems.length >= minFields) {
					DataItem d = new DataItem();
					String item_name = lineItems[0];
					d.setItem_name(item_name);
					String shade_name = lineItems[1];
					d.setShade_name(shade_name);
					int mrp = Integer.parseInt(lineItems[lineItems.length - 1]);
					d.setMrp(mrp);
					List<Size_Quantity> size_quantity_list = new ArrayList<Size_Quantity>();
					int sizeIndex = 2;
					for (Integer i : sizes) {
						Size_Quantity size_quantity = new Size_Quantity();
						size_quantity.setSize(i);
						String quantity=lineItems[sizeIndex];
						if(!quantity.equalsIgnoreCase(""))
							size_quantity.setQuantity(Integer.parseInt(quantity));
						else
							size_quantity.setQuantity(0);
						size_quantity_list.add(size_quantity);
						sizeIndex++;
					}
					d.setSize_quantity(size_quantity_list);
					data.add(d);
					/*
					 * if(lineItems.length>3){ int
					 * rowItems=lineItems.length-minFields; List<DataItem>
					 * data=new ArrayList<DataItem>(); List<Size_Quantity>
					 * size_quantity_list=new ArrayList<Size_Quantity>(); int
					 * sizeIndex=2; for(Integer i:sizes) { Size_Quantity
					 * size_quantity=new Size_Quantity();
					 * size_quantity.setSize(i);
					 * size_quantity.setQuantity(lineItems[sizeIndex]);
					 * size_quantity_list.add(size_quantity); sizeIndex++; }
					 * for(int i=2;i<lineItems.length-2;i++){ DataItem d=new
					 * DataItem; d.setItemName(item_name);
					 * d.setShadeName(shade_name); d.setMrp(mrp);
					 * d.setSize(sizes[sizeIndex]); d.setQuantity(lineItems[i]);
					 * data.add(d); sizeIndex++; } }
					 */
				}
			}
			bf.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity(data).build();
	}
}
