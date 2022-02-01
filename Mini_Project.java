package com.company.fileoperations;
import java.io.*;
import java.util.Scanner;
class Library_Management_System {
    void create_file() {
        try {
            //to create file which contains all books
            File f = new File("All_books.txt");
            //to check created file
            if (!f.exists()) {
                f.createNewFile();{
                    System.out.println("File created: " + f.getName());
                }
            } else {
                System.out.println("file exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //to create file which contains issued books
        try {
            File f = new File("updated.txt");
            File fe = new File("issued_book.txt");
            if (!f.exists()) { f.createNewFile(); }
            if (!fe.exists()) { fe.createNewFile(); }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void add() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the book name:");
        String b_name = sc.next();

        System.out.print("Enter the author name:");
        String a_name = sc.next();

        System.out.print("Enter the book publication:");
        String p_name = sc.next();

        try {
            FileWriter f = new FileWriter("All_books.txt", true);
            f.write(b_name + " " + a_name + " " + p_name + "\n");
            System.out.println("Successfully wrote to the file. ");
            f.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    void view_book() {
        File myReader1 = new File("All_books.txt");
        if (myReader1.length() == 0) {
            System.out.println("File is empty!!");
        } else {
            try {
                Scanner sc = new Scanner(myReader1);
                try {
                    while (sc.hasNextLine()) {
                        String line = sc.nextLine();
                        String[] str = line.split("\\s+");
                        System.out.print("book name:" + str[0] + "\nauthor name:" + str[1] + "\nbook publication:" + str[2]);
                        System.out.println("\n");
                    }
                } catch (IndexOutOfBoundsException f) {
                    System.out.println("error: Index out of bound");
                }
                sc.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error has occured");
                e.printStackTrace();
            }
        }
    }

    void Issue_book(){

        Scanner sc = new Scanner(System.in);
        System.out.println("which book do you want");
        System.out.print("Enter name of book:");
        String issu_bk_name = sc.next();
        System.out.println(issu_bk_name);

        try {
            File myRead = new File("All_books.txt");
            FileWriter f = new FileWriter("issued_book.txt", true);
            Scanner sn = new Scanner(myRead);
            while (sn.hasNextLine()) {
                String line = sn.nextLine();
                String[] str = line.split("\\s");
                System.out.println("issu_bk_name: " + issu_bk_name);
                Object issu = issu_bk_name;
                if (str[0].equals(issu_bk_name))
                {
                    f.write(str[0] + " " + str[1] + " " + str[2] + "\n");
                    System.out.println("Succesfully written");
                    break;
                }
            }
            f.close();
        } catch (IOException e) {
            System.out.println("error in issue book");
            e.printStackTrace();
        }
    }

    void view_issued_books() {

        try {
            File myReader2 = new File("issued_book.txt");
            Scanner sc = new Scanner(myReader2);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] str = line.split("\\s+");
                System.out.print("book name:" + str[0] + "\nauthor name:" + str[1] + "\nbook publication:" + str[2] + "\n");
                System.out.println();
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occured");
            e.printStackTrace();
        }
    }


    void Return_a_book() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name of the book which is to be returned:");
        String ret_bk = sc.next();
        //to write the content of issued book in new temporary file named updated except ret_book
        try {
            PrintWriter pw = new PrintWriter("updated.txt");
            sc = new Scanner(new FileReader("issued_book.txt"));

            while (sc.hasNextLine())
            {
                boolean flag = false;
                String line1 = sc.nextLine();
                String[] str = line1.split("\\s+");
                Object obj = str[0];
                if (ret_bk.equals(obj))
                {
                    flag = true;
                }
                if (!flag) {
                    pw.println(line1);
                }
            }
            pw.flush();
            pw.close();

            //to write the content of updated file in issued book file
            pw = new PrintWriter("issued_book.txt");
            sc = new Scanner(new FileReader("updated.txt"));
            while (sc.hasNextLine()) {
                String line1 = sc.nextLine();
                pw.println(line1);
            }
            pw.flush();
            pw.close();

        } catch (FileNotFoundException fileNotFoundException)
        {
            fileNotFoundException.printStackTrace();
        }
    }
}

public class Mini_Project
{
    public static void main(String[] args) throws FileNotFoundException, NoSuchFieldException {
        Library_Management_System obj= new Library_Management_System();
        Scanner sc=new Scanner(System.in);
        boolean tr=true;
        System.out.println("Welcome to Library");
        obj.create_file();
        while(tr)
        {
            System.out.println("\nWhat you would like to do?");
            System.out.println("1.Add book");
            System.out.println("2.view book");
            System.out.println("3.Issue book");
            System.out.println("4.view issued book");
            System.out.println("5.Return a book");
            System.out.println("6.Exit");
            System.out.print("Enter your choice: ");
            int choice=sc.nextInt();
            switch(choice)
            {
                case 1: obj.add();
                    break;

                case 2: obj.view_book();
                    break;

                case 3:
                    obj.Issue_book();
                    break;

                case 4: obj.view_issued_books();
                    break;

                case 5: obj.Return_a_book();
                    break;

                case 6: tr=false;
                    break;

                default:
                    System.out.println("Invalid choice!");

            }

        }
    }
}