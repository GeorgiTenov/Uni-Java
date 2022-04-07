package uni.task.warehouse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Warehouse {

    private List<Material> materials;

    private final long maximumStorage = 10000L;

    private long firstMatYearNumbers;

    private long secondMatYearNumbers;

    private int firstMatMonthNumbers;

    private int secondMatMonthNumbers;

    private int firstMatDayNumber;

    private int secondMatDayNumber;

    public Warehouse(){
        this.materials = new ArrayList<>();
    }

    public void addMaterials(Material ...materials){
        Arrays.stream(materials)
                .forEach(material -> {
                    if(this.materials.size() <= maximumStorage)
                        this.materials.add(material);
                });

    }

    public void removeMaterial(Material material){
        materials.remove(material);
    }

    public void printAllMaterials(){
        materials.stream()
                .sorted(Comparator.comparing(Material::getStoragePosition))
                .forEach(material -> System.out.println(material));
    }

    private List<Material> getAllSpecialMaterials(){

        return materials.stream()
                .filter(material -> material.getStockGroup().equalsIgnoreCase("E"))
                .collect(Collectors.toList());
    }

    public void printAllSpecialMaterials(){
        List<Material> specialMaterials = getSortedMaterials(getAllSpecialMaterials());
        specialMaterials.stream().forEach(material -> System.out.println(material));
    }

    private List<Material> getAllSpecialMaterialsWithCode(String code){
        List<Material> materialsFound = materials.stream()
                .filter(material -> material.getCode().equalsIgnoreCase(code))
                .filter(material -> material.getStockGroup().equalsIgnoreCase("E"))
                .sorted(Comparator.comparing(Material::getStoragePosition))
                .collect(Collectors.toList());

        return materialsFound;
    }

    private float getMinimumHumidity(List<Material> mats){
        AtomicReference<Float> minimumHumidity = new AtomicReference<>(mats.get(0).getHumidityPercentage());

        mats.stream()
                .forEach(material -> {
                    if(material.getStockGroup().equalsIgnoreCase("e")){
                        if(material.getHumidityPercentage() < minimumHumidity.get())
                            minimumHumidity.set(material.getHumidityPercentage());
                    }
                });

        return minimumHumidity.get();
    }

    public void printSpecialMaterials(String code){
        List<Material> filteredMaterials = getAllSpecialMaterialsWithCode(code);

        int materialsCount = filteredMaterials.size();

        float minimumHumidity = 0f;

        if(materialsCount > 0f)
           minimumHumidity = getMinimumHumidity(filteredMaterials);

        filteredMaterials.stream()
                        .forEach(material -> System.out.println(material));

        System.out.println("Count: " + materialsCount);
        System.out.println("Minimum Humidity: " + minimumHumidity);

    }

    public List<Material> getSortedMaterials(List<Material> specialMaterials){

        for(int i=0;i < specialMaterials.size();i++){
            for(int j=i+1;j < specialMaterials.size();j++){
                String firstMatYear = specialMaterials.get(i).getDateOfEntrance().substring(6);
                String secondMatYear = specialMaterials.get(j).getDateOfEntrance().substring(6);

                firstMatYearNumbers = Long.parseLong(firstMatYear);
                secondMatYearNumbers = Long.parseLong(secondMatYear);

                String firstMatMonth = materials.get(i).getDateOfEntrance().substring(3,5);
                String secondMatMonth = materials.get(j).getDateOfEntrance().substring(3,5);

                if(firstMatMonth.startsWith("0"))
                    firstMatMonth = firstMatMonth.substring(1);

                if(secondMatMonth.startsWith("0"))
                    secondMatMonth = secondMatMonth.substring(1);

                firstMatMonthNumbers = Integer.parseInt(firstMatMonth);
                secondMatMonthNumbers = Integer.parseInt(secondMatMonth);

                String firstMatDay = specialMaterials.get(i).getDateOfEntrance().substring(0,2);
                String secondMatDay = specialMaterials.get(j).getDateOfEntrance().substring(0,2);

                if(firstMatDay.startsWith("0"))
                    firstMatDay = firstMatDay.substring(1);

                if(secondMatDay.startsWith("0"))
                    secondMatDay = secondMatDay.substring(1);

                firstMatDayNumber = Integer.parseInt(firstMatDay);
                secondMatDayNumber = Integer.parseInt(secondMatDay);

                boolean areDatesEqual = firstMatYearNumbers == secondMatYearNumbers
                        && firstMatMonthNumbers == secondMatMonthNumbers
                        && firstMatDayNumber == secondMatDayNumber;

                //CHECK FOR EQUAL DATE
                if(areDatesEqual){
                    sortByLastSellDate(i,j,specialMaterials);
                    continue;
                }
                //CHECK FOR FIRST MATERIAL > SECOND
                if(firstMatYearNumbers > secondMatYearNumbers){
                    if(i < j){
                        swap(j,i,specialMaterials);
                    }


                }else if(firstMatYearNumbers == secondMatYearNumbers){
                    if(firstMatMonthNumbers > secondMatMonthNumbers){
                        if(i < j){
                            swap(j,i,specialMaterials);
                        }
                    }else if(firstMatMonthNumbers == secondMatMonthNumbers){
                        if(firstMatDayNumber > secondMatDayNumber){
                            if(i < j){
                                swap(j,i,specialMaterials);
                            }
                        }
                    }
                }

                //CHECK FOR SECOND MATERIAL > FIRST
                if(firstMatYearNumbers < secondMatYearNumbers){

                    if(j < i){
                       swap(i,j,specialMaterials);
                    }


                }else if(firstMatYearNumbers == secondMatYearNumbers){
                    if(firstMatMonthNumbers < secondMatMonthNumbers){

                        if(j < i){
                           swap(i,j,specialMaterials);
                        }

                    }else if(firstMatMonthNumbers == secondMatMonthNumbers){
                        if(firstMatDayNumber < secondMatDayNumber){

                            if(j < i){
                               swap(i,j,specialMaterials);
                            }
                        }
                    }
                }

            }

        }

        return specialMaterials;
    }

    private void sortByLastSellDate(int i,int j,List<Material> specialMaterials){
        if(firstMatYearNumbers == secondMatYearNumbers
                && firstMatMonthNumbers == secondMatMonthNumbers
                && firstMatDayNumber == secondMatDayNumber){

            if(specialMaterials.get(i).getLastSellDays() > specialMaterials.get(j).getLastSellDays()){
                if(i > j){
                 swap(i,j,specialMaterials);
                }
            }else if(specialMaterials.get(i).getLastSellDays() < specialMaterials.get(j).getLastSellDays()){
                if(j > i){
                    swap(i,j,specialMaterials);
                }
            }
        }
    }

    private void swap(int i,int j,List<Material> specialMaterials){
        Material temp = specialMaterials.get(j);
        specialMaterials.set(j,specialMaterials.get(i));
        specialMaterials.set(i,temp);
    }

}
