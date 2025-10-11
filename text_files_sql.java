/* TODO - Take the names of the text-files and store their info 
*           - file_name, word_count, date_added 
*
 */
import java.io.File;

public class text_files_sql {

    public String text_output_path;
    public String text_path;

    public text_files_sql(String text_output_path, String text_path) {
        this.text_output_path = text_output_path;
        this.text_path = text_path;
    }

    public void check_path() {

        File file1 = new File(this.text_output_path);
        File file2 = new File(this.text_path);

        if ((file1.exists() && file1.isDirectory()) || (file2.exists() && file2.isDirectory())) {
            System.out.println("Directory exists");
        } else {

            System.out.println("Path not found");

        }

    }
    // SQL Connection in main 

    public static void main(String args[]) {
        text_files_sql testPath = new text_files_sql("test-files-output-copy/", "text-files/");
        testPath.check_path();

    }

}
