package currencycalculator;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

final class Converter{

    public String first, second;
    public float result;
    
    public Converter(float factor, String first, String second){
        this.first = first;
        this.second = second;
        try {
            this.result = factor * this.getQuote(first, second);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public float getQuote(String firstCurrency, String secondCurrency) throws MalformedURLException{
        StringBuilder sb = new StringBuilder();
        String path = "http://download.finance.yahoo.com/d/quotes.csv?s=" + firstCurrency + secondCurrency + "=X&f=l1&e=.cs";
        URL url = new URL(path);
        
        try (InputStream in = url.openStream()) {
            int bytes;
            while((bytes = in.read()) != -1){
                sb.append((char)bytes);
            }
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error reading URL");
        }
        String f = sb.toString();
        return Float.parseFloat(f);
    }
    
    public static ArrayList<String> comboList(){
        String file = "src/currencyCalculator/bist.csv";
        String line;
        ArrayList<String> list = new ArrayList<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader(file))){

            while((line = br.readLine()) != null) {
                list.add(line);
            }   
            br.close();         
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Cannot open File" + file);
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Error reading File" + file);
        }
        System.out.println(list.size());
        return list;
    }
    
        
}
