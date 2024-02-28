package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Inquiry {
    Scanner scanner = new Scanner(System.in);

    String inquiryID; // 문의 아이디
    String inquiryDetail; // 문의 내용
    String[] ID_list = new String[1]; // 문의 아이디 리스트
    String[] detail_list = new String[1]; // 문의 내용 리스트


    public void Get_Inquiry_Info(){
        System.out.println("\n----- 문의 남기기 -----");
        System.out.print("문의 아이디: ");
        inquiryID = scanner.next();
        scanner.nextLine(); // nextLine() 메소드를 통해 next()에서 없어지지 않은 '\n'을 없애주기
        System.out.print("문의 내용: ");
        inquiryDetail = scanner.nextLine();
    }

    public void Add_Inquiry_Info(){
        // 배열을 ArrayList로 바꾸어 null값을 제거한 후 문의 아이디와 내용을 각각 ArrayList에 넣어준다.
        ArrayList<String> new_ID_list = new ArrayList<>(Arrays.asList(ID_list));
        ArrayList<String> new_detail_list = new ArrayList<>(Arrays.asList(detail_list));
        new_ID_list.remove(null);
        new_detail_list.remove(null);
        new_ID_list.add(inquiryID);
        new_detail_list.add(inquiryDetail);
        ID_list = new_ID_list.toArray(new String[0]);
        detail_list = new_detail_list.toArray(new String[0]);

        System.out.println("\n문의가 저장되었습니다!\n");
    }

    public void Show_Inquiry_List() {
        System.out.println("\n----- 문의 리스트 보기 -----");
        if (ID_list[0] == null) {
            System.out.println("\n문의사항이 없습니다. 문의 남기기를 통해 문의해주세요!\n");
        } else {
            for (int i = 0; i < ID_list.length; i++) {
                System.out.println("문의 아이디:" + ID_list[i]);
                System.out.println("문의 내용: " + detail_list[i] + "\n");
            }
        }
    }
}
