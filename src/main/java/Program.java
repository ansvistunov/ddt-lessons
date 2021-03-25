/**
 * @author : Alex
 * @created : 04.03.2021, четверг
 **/
public class Program {
    enum Day{
        MONDAY(12),
        TUESDAY(6),
        WEDNESDAY(6),
        THURSDAY(8),
        FRIDAY(6),
        SATURDAY(6){
            double pay(double hourlyRate){return 1.5 * hourlyRate * workingHours;}
        },
        SUNDAY(1){
            double pay(double hourlyRate){return 2 * hourlyRate * workingHours;}
        };
        public boolean isHolyday(){
            switch(this){
                case SUNDAY:
                case SATURDAY: return true;
                default:return false;
            }
        }
        Day(int workingHours){
            this.workingHours = workingHours;
        }
        double pay(double hourlyRate){return hourlyRate*workingHours;}
        final int workingHours;
    }

    public static void main(String[] args) {
        Day current = Day.SATURDAY;
        Day day = Day.valueOf("MONDAY"); //загрузка из строки
        Day[] days = Day.values(); //массив всех дней
        int number = Day.FRIDAY.ordinal(); //порядковый номер
        String name = Day.FRIDAY.name(); //имя ("FRIDAY")
        System.out.println(current.isHolyday());
        System.out.println(current.pay(1500));
        System.out.println(Day.FRIDAY.pay(1500));
    }
}
