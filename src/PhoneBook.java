import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class PhoneBook {
    /*
        문제 설명
            전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
            전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다.

            구조대 : 119
            박준영 : 97 674 223
            지영석 : 11 9552 4421
            전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때, 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.

        제한 사항
            phone_book의 길이는 1 이상 1,000,000 이하입니다.
            각 전화번호의 길이는 1 이상 20 이하입니다.

        입출력 예제
            phone_book	                    return
            --------------------------------------
            [119, 97674223, 1195524421]	    false
            [123,456,789]	                true
            [12,123,1235,567,88]	        false
     */


    /**
     * Solution1 : 단순 이중 Loop를 이용한 판별
     *
     * @param phone_book
     * @return
     */
    public boolean solution1(String[] phone_book) {
        boolean answer = true;
        List<String> phoneBooks = Arrays.asList(phone_book);

        for (int i = 0; i < phone_book.length; i++) {
            // 선행 반복에서 answer가 false 된 경우 반복 종료
            if (!answer) {
                break;
            }


            for (int j = 0; j < phone_book.length; j++) {
                // 자기 자신이면  continue
                if (i == j) {
                    continue;
                }

                //  base가 target 보다 길면 continue
                if (phone_book[i].length() > phone_book[j].length()) {
                    continue;
                }

                // base가 target의 접두어인 경우 false 반환
                if (phone_book[i].equals(phone_book[j].substring(0, phone_book[i].length()))) {
                    answer = false;
                    break;
                }

                System.out.println("base : " + phone_book[i] + ", target : " + phone_book[j]);
            }
        }
        return answer;
    }

    /**
     * Solution2 : solution1 리팩토링 (StartWith 사용)
     *
     * @param phone_book
     * @return
     */
    public boolean solution2(String[] phone_book) {
        for (int i = 0; i < phone_book.length; i++) {
            for (int j = 0; j < phone_book.length; j++) {
                // 자기 자신이면  continue
                if (i == j) {
                    continue;
                }

                // base가 target의 접두어인 경우 false 반환
                if (phone_book[j].startsWith(phone_book[i])) {
                    return false;
                }

                //System.out.println("base : " + phone_book[i] + ", target : " + phone_book[j]);
            }
        }
        return true;
    }


    /***
     * Solution3: HashSet 이용
     * @param phone_book
     * @return
     */
    public boolean solution3(String[] phone_book) {
        HashSet<String> hashSet = new HashSet<>();

        //Arrays.sort(phone_book, Comparator.comparingInt(String::length).reversed());
        Arrays.sort(phone_book, (o1, o2) -> o2.length() - o1.length());

        for(String phone: phone_book){
            if(hashSet.contains(phone)){
                return false;
            }

            for(int i = 1; i< phone.length(); i++) {
                hashSet.add(phone.substring(0, i));
            }
        }

        return true;
    }


    public static void main(String[] args) {
        String[] phone_book = {"119", "97674223", "1195524421"};
        PhoneBook pb = new PhoneBook();
        //System.out.println("solution1 : " + pb.solution1(phone_book));
        //System.out.println("solution2 : " + pb.solution2(phone_book));
        System.out.println("solution3 : " + pb.solution3(phone_book));
    }

}
