package uni.task.warehouse;

public class Material {

    private String code;

    private final int maxCodeSymbols = 4;

    private String name;

    private final int maxNameSymbols = 55;

    private int quantity;

    private int lastSellDays;

    private String stockGroup;

    private String dateOfEntrance;

    private String storagePosition;

    private final int maxStoragePosition = 10;

    private float humidityPercentage;

    public Material(){

    }

    public Material(String code,
                    String name,
                    int quantity,
                    int lastSellDays,
                    String stockGroup,
                    String dateOfEntrance,
                    String storagePosition,
                    float humidityPercentage){

        if(code.length() <= maxCodeSymbols && code.length() > 0)
            this.code = code;

        else
            System.out.println("Could not set code");

        if(name.length() <= maxNameSymbols && name.length() > 0)
            this.name = name;

        else
            System.out.println("Could not set name");

        this.quantity = quantity;

        this.lastSellDays = lastSellDays;

        this.stockGroup = stockGroup;

        this.dateOfEntrance = dateOfEntrance;

        if(storagePosition.length() <= maxStoragePosition && storagePosition.length() > 0)
            this.storagePosition = storagePosition;

        else
            System.out.println("Could not set storage position");

        this.humidityPercentage = humidityPercentage;

    }

    public void setCode(String code){
        if(code.length() <= maxCodeSymbols && code.length() > 0)
            this.code = code;

        else
            System.out.println("Could not set code");
    }

    public String getCode(){
        return this.code;
    }

    public void setName(String name){
        if(name.length() <= maxNameSymbols && name.length() > 0)
            this.name = name;

        else
            System.out.println("Could not set name");
    }

    public String getName(){
        return this.name;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void setLastSellDays(int lastSellDays){
        this.lastSellDays = lastSellDays;
    }

    public int getLastSellDays(){
        return this.lastSellDays;
    }

    public void setStockGroup(String stockGroup){
        this.stockGroup = stockGroup;
    }

    public String getStockGroup(){
        return this.stockGroup;
    }

    public void setDateOfEntrance(String dateOfEntrance){
        this.dateOfEntrance = dateOfEntrance;
    }

    public String getDateOfEntrance(){
        return this.dateOfEntrance;
    }

    public void setStoragePosition(String storagePosition){
        if(storagePosition.length() <= maxStoragePosition && storagePosition.length() > 0)
            this.storagePosition = storagePosition;

        else
            System.out.println("Could not set storage position");
    }

    public String getStoragePosition(){
        return this.storagePosition;
    }

    public void setHumidityPercentage(float humidityPercentage){
        this.humidityPercentage = humidityPercentage;
    }

    public float getHumidityPercentage(){
        return this.humidityPercentage;
    }

    @Override
    public String toString() {
        String specialMaterialinfo;

        if(this.stockGroup.equalsIgnoreCase("E")){
            specialMaterialinfo = storagePosition
                    + ", "
                    + code
                    + ", "
                    + name
                    + ", "
                    + quantity
                    + ", "
                    + dateOfEntrance
                    + ", "
                    + lastSellDays
                    + ", "
                    + "B% = "
                    + humidityPercentage;
        }else{
            specialMaterialinfo = storagePosition
                    + ", "
                    + code
                    + ", "
                    + name
                    + ", "
                    + quantity
                    + ", "
                    + dateOfEntrance
                    + ", "
                    + lastSellDays;
        }

        return specialMaterialinfo;

    }
}
