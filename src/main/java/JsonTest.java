import org.json.*;

/**
 * @author Michelle.Chen
 */
public class JsonTest {
    private String a;
    private String b;

    public static void main(String[] args) {
        String response_data="{\n" +
            "    \"formatted_price\": \"$16.00\",\n" +
            "    \"cuisine_name\": \"Mexican\",\n" +
            "    \"dinner_rush_sign_up_status\": null,\n" +
            "    \"spice_level\": null,\n" +
            "    \"restaurant_status\": \"AVAILABLE\",\n" +
            "    \"restaurant_id\": \"2c987289-ee6e-49ec-af53-269111c91f72\",\n" +
            "    \"nutrition_fact\": {\n" +
            "        \"quantity\": null,\n" +
            "        \"servings_per_dish\": \"1\",\n" +
            "        \"ingredient_desc\": \"Skirt Steak, Water, White Corn Tortilla, Plum Tomato, White Onion, Hass Avocado, Lime, Tomatillo, Garlic, Red Onion, Cilantro, Grapeseed Oil, Guajillo Chile, Ancho Pepper, Jalapeno Pepper, Kosher Salt, Avocado Leaves, Micro Cilantro, Ground Cumin, Black Pepper, Canola Oil, Cinnamon, Dried Bay Leaf, Cayenne Pepper, Dried Oregano, Clove\",\n" +
            "        \"round_carbs_text\": \"60g\",\n" +
            "        \"round_sodium_text\": \"1160mg\",\n" +
            "        \"round_fiber_text\": \"10g\",\n" +
            "        \"round_calories_text\": \"780\",\n" +
            "        \"round_total_fat_text\": \"29g\",\n" +
            "        \"round_cholesterol_text\": \"120mg\",\n" +
            "        \"round_trans_fat_text\": \"1g\",\n" +
            "        \"unit\": null,\n" +
            "        \"servings_size_quantity\": \"1\",\n" +
            "        \"servings_size_unit\": \"order\",\n" +
            "        \"round_saturated_fat_text\": \"8g\",\n" +
            "        \"round_protein_text\": \"46g\",\n" +
            "        \"round_sugar_text\": \"4g\"\n" +
            "    },\n" +
            "    \"customizable\": true,\n" +
            "    \"large_images\": [\n" +
            "        {\n" +
            "            \"image_url\": \"https:\\/\\/rfstaging-image.azureedge.net\\/image\\/origin\\/product\\/c942f132-36ec-4fa2-b2e7-b842f1c77eaf.jpg\",\n" +
            "            \"original_file_name\": \"Barrio_Tacos Barbacoa (Lifestyle).jpg\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"diets\": [\n" +
            "        \"GLUTEN_FREE\"\n" +
            "    ],\n" +
            "    \"max_available_qty\": 0,\n" +
            "    \"small_image_url\": \"https:\\/\\/rfstaging-image.azureedge.net\\/image\\/origin\\/product\\/812923a8-bd67-4ee7-8d35-77af07b46b44.png\",\n" +
            "    \"nutrition_facts_pdf_name\": null,\n" +
            "    \"price\": 16.0,\n" +
            "    \"byo_allergens_disclaimer\": null,\n" +
            "    \"base_price\": 16.0,\n" +
            "    \"id\": \"227975bf-a583-4557-96a6-311a03cf84fa\",\n" +
            "    \"is_byo_item\": false,\n" +
            "    \"featured_options\": [\n" +
            "    ],\n" +
            "    \"opening_time\": null,\n" +
            "    \"long_description\": \"Marinated, braised skirt steak served on housemade corn tortillas, topped with red onion, fresh cilantro, and ripe avocado. Served with tangy salsa verde\",\n" +
            "    \"spicy\": false,\n" +
            "    \"special_requests\": [\n" +
            "        {\n" +
            "            \"category_name\": \"REMOVE\",\n" +
            "            \"options\": [\n" +
            "                {\n" +
            "                    \"min_choices\": 0,\n" +
            "                    \"selection_hint\": \"Optional\",\n" +
            "                    \"max_choices\": null,\n" +
            "                    \"free_until\": null,\n" +
            "                    \"ui_type\": \"NORMAL\",\n" +
            "                    \"type_name\": \"OPTIONAL_SUBTRACTION\",\n" +
            "                    \"values\": [\n" +
            "                        {\n" +
            "                            \"formatted_price\": \"$0.00\",\n" +
            "                            \"image_url\": null,\n" +
            "                            \"option_value_item_number\": \"8800032\",\n" +
            "                            \"description\": null,\n" +
            "                            \"display_on_summary\": \"No avocado\",\n" +
            "                            \"max_available_qty\": null,\n" +
            "                            \"allergens\": [\n" +
            "                            ],\n" +
            "                            \"is_none_type\": null,\n" +
            "                            \"usage_quantity_in_cart\": 0,\n" +
            "                            \"price\": 0.0,\n" +
            "                            \"name\": \"No Avocado\",\n" +
            "                            \"calories_k_cal\": 160.0,\n" +
            "                            \"id\": \"98731d11-685a-4362-9c7e-93380a81c7be\",\n" +
            "                            \"selected\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"formatted_price\": \"$0.00\",\n" +
            "                            \"image_url\": null,\n" +
            "                            \"option_value_item_number\": \"8800020\",\n" +
            "                            \"description\": null,\n" +
            "                            \"display_on_summary\": \"No cilantro\",\n" +
            "                            \"max_available_qty\": null,\n" +
            "                            \"allergens\": [\n" +
            "                            ],\n" +
            "                            \"is_none_type\": null,\n" +
            "                            \"usage_quantity_in_cart\": 0,\n" +
            "                            \"price\": 0.0,\n" +
            "                            \"name\": \"No Cilantro\",\n" +
            "                            \"calories_k_cal\": 25.0,\n" +
            "                            \"id\": \"3b07e456-b17d-42d6-9f37-ee9bc5466481\",\n" +
            "                            \"selected\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"formatted_price\": \"$0.00\",\n" +
            "                            \"image_url\": null,\n" +
            "                            \"option_value_item_number\": \"8800028\",\n" +
            "                            \"description\": null,\n" +
            "                            \"display_on_summary\": \"No onion\",\n" +
            "                            \"max_available_qty\": null,\n" +
            "                            \"allergens\": [\n" +
            "                            ],\n" +
            "                            \"is_none_type\": null,\n" +
            "                            \"usage_quantity_in_cart\": 0,\n" +
            "                            \"price\": 0.0,\n" +
            "                            \"name\": \"No Onion\",\n" +
            "                            \"calories_k_cal\": 40.0,\n" +
            "                            \"id\": \"ce720317-e7e5-4367-b690-ade2be36b6ce\",\n" +
            "                            \"selected\": false\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"name\": \"Special Requests\",\n" +
            "                    \"id\": \"a067de0f-e543-4df3-a291-43d9504e895e\",\n" +
            "                    \"selected_choices\": 0\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ],\n" +
            "    \"allergens\": [\n" +
            "    ],\n" +
            "    \"usage_quantity_in_cart\": 0,\n" +
            "    \"formatted_base_price\": \"$16.00\",\n" +
            "    \"show_raw_foods_disclaimer\": false,\n" +
            "    \"name\": \"Tacos Barbacoa\",\n" +
            "    \"user_allergens\": [\n" +
            "    ],\n" +
            "    \"nutrition_facts_pdf_url\": null\n" +
            "}";
        JSONObject response_obj=new JSONObject(response_data);
        JSONArray featuresArray=response_obj.getJSONArray("featured_options");
        String string = response_obj.getString("");
        JSONArray resultArray=new JSONArray();

        if(featuresArray.length()!=0){
            for(int i=0;i<featuresArray.length();i++){
                JSONObject featuresObj=featuresArray.getJSONObject(i);
                JSONArray values=featuresObj.getJSONArray("values");

                JSONObject jsonObject = values.getJSONObject(0);
                JSONObject obj=new JSONObject();
                obj.put("option_id",featuresObj.get("id"));

                String[] ids=new String[]{values.getJSONObject(0).get("id").toString()};
                obj.put("option_value_ids",ids);

                resultArray.put(obj);
            }
        }
        System.out.println("resultArray = " + resultArray);
    }
}
