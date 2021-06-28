/*Define an array of strings languages, containing {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"}
Generate a random integer n: int n = (int) (Math.random() * 1_000_000);
Compute the result obtained after performing the following calculations:
  multiply n by 3;
  add the binary number 10101 to the result;
  add the hexadecimal number FF to the result;
  multiply the result by 6;
Compute the sum of the digits in the result obtained in the previous step. This is the new result. While the new result has more than one digit, continue to sum the digits of the result.
Display on the screen the message: "Willy-nilly, this semester I will learn " + languages[result].*/

public class TaskB {
    public static void main(String[] args) {

        String[] languages={"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

        for(int i=0;i< languages.length;++i)
            System.out.println(languages[i]);

        int n = (int) (Math.random()*1000000);
        System.out.println(n);

        int result=n*3;
        result+=0b10101;
        result+=0xFF;
        result*=6;

        System.out.println(result);
        while(result>9){
         int aux=0;
         while(result!=0){
            aux+=result%10;
            result/=10;
         }
         result=aux;
         System.out.println(result);
        }
        System.out.println(result);

        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }
}