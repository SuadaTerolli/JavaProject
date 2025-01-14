package sales.inventory;
public class Sector {
    private int sectorId;
    private String name;
    public Sector(){

    }
    public Sector(int sectorId, String name){
        this.sectorId=sectorId;
        this.name=name;
        this.items=items;
    }
    public int getSectorId(){
        return sectorId;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public ArrayList<Item> getItems() {
        return items;
    }

}
