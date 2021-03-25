package inner;

/**
 * @author : Alex
 * @created : 04.03.2021, четверг
 **/
public class TopClass {
    private int multiplicator = 10;
    class InnerClass1{
        String name;
        public void setName(String name){this.name = name.repeat(multiplicator);}
    }
    InnerClass1 value;
    public void setValue(InnerClass1 value){this.value = value;}

    public Object returnInnerClass(){
        class InnerClass2{
            int x, y;
            InnerClass2(int x, int y){
                this.x = x * multiplicator; this.y = y * multiplicator;
            }
        }
        return new InnerClass2(10,20);
    }
}
