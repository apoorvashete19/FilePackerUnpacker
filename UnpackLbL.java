import java.util.*;
import java.io.*;

class UnpackLbL
{
    public static void main(String arg[]) throws Exception
    {
        // Print the initial banner
        System.out.println("------------------------------------------------");
        System.out.println("---------- Marvellous Packer Unpacker ----------");
        System.out.println("------------------------------------------------");

        // Create a byte array to hold the file header information
        byte Header[] = new byte[100];
        // Variable to hold the size of the file to be unpacked
        int FileSize = 0;
        // String variable to hold temporary data
        String str = null;
        // Scanner object for user input
        Scanner sobj = new Scanner(System.in);
        // Variable to hold the number of bytes read
        int iRet = 0;
        // Counter to keep track of the number of files unpacked
        int iCnt = 0;

        // Prompt the user to enter the name of the packed file
        System.out.println("Enter the name of packed file that you want to unpack : ");
        // Read the user's input for the packed file name
        String PackedFile = sobj.nextLine();

        // Create a File object for the packed file
        File fobj = new File(PackedFile);
        // Create a FileInputStream object to read from the packed file
        FileInputStream fiobj = new FileInputStream(fobj);

        // Read the header from the packed file until the end of the file
        while((iRet = fiobj.read(Header,0,100)) > 0)		//after end of file header becomes 0, so code goes out of while loop
        {
            // Convert the header byte array to a string
            String Hstr = new String(Header);

            // Trim the header string to remove extra white spaces
            str = Hstr.trim();
            // Split the header string to extract the file name and file size
            String Tokens[] = str.split(" ");

            // Create a new file using the extracted file name
            File NewFile = new File(Tokens[0]);
            NewFile.createNewFile();

            // Parse the file size from the extracted tokens
            FileSize = Integer.parseInt(Tokens[1]);

            // Create a byte array to hold the file data
            byte Buffer[] = new byte[FileSize];
            // Read the file data from the packed file
            fiobj.read(Buffer,0,FileSize);

            // Create a FileOutputStream object to write to the new file
            FileOutputStream foobj = new FileOutputStream(NewFile);
            // Write the file data to the new file
            foobj.write(Buffer,0,FileSize);

            // Close the FileOutputStream
            foobj.close();

            // Increment the file counter
            iCnt++;
        }

        // Close the FileInputStream
        fiobj.close();

        // Print completion messages
        System.out.println("Unpacking activity completed..");
        System.out.println("Total file unpacked successfully : "+iCnt);

        System.out.println("------------------------------------------------");
        System.out.println("Thank you for using Marvellous Packer Unpacker");
        System.out.println("------------------------------------------------");
    }
}
