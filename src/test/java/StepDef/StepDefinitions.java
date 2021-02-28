package StepDef;

import PageObjects.DatePicker;
import PageObjects.RandomDateGenerator;
import PageObjects.WebTablePrinting;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import resources.BaseClass;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class StepDefinitions extends BaseClass{

    //1
        @Given("^Initialize The Driver$")
        public void initialize_the_driver() throws Throwable {
            System.out.println("print all the values present in a web table");
            driver = initializerDriver();
        }

        @When("^Navigate the WebPage to (.+)$")
        public void navigate_the_webpage_to(String url) throws Throwable {
            driver.get(url);
        }

        WebTablePrinting wt;

        @Then("^Print The Values from WebTable$")
        public void print_the_values_from_webtable() throws Throwable {
            wt = new WebTablePrinting(driver);
            for (int i = 0; i < wt.colomn(); i++) {
                String header = wt.ColomnHeader().get(i).getText();
                System.out.format("%-35s", header);
            }
            System.out.println("");
            wt.TableData();
            System.out.println();
        }

        //2
        String str;

        @Given("^Take (.+) As a String$")
        public void take_as_a_string(String input) throws Throwable {
            System.out.println("Sorting the string");
            str = input;
            System.out.println("given string is " + str);
        }

        @When("^arrange the all characters of the string in sorting order$")
        public void arrange_the_all_characters_of_the_string_in_sorting_order() throws Throwable {
            str = Stream.of(str.split("")).sorted(Comparator.comparingInt(o -> Character.toLowerCase(o.charAt(0)))).distinct().collect(Collectors.joining());

        }

        @Then("^Print the Sorted String in Console$")
        public void print_the_sorted_string_in_console() throws Throwable {
            System.out.println("String After sorting and removing duplicates " + str);
            System.out.println("");
        }



        //3
        WebDriver driver;
        LocalDate date;
        Calendar c;

        @Given("^Get The Current Date$")
        public void get_the_current_date() throws Throwable {
            System.out.println("checking if the current date is a working day");
            date = LocalDate.now();
            c = Calendar.getInstance();
        }

        int dayOfWeek;

        @When("^Check the day that if it is Working Day or Not$")
        public void check_the_day_that_if_it_is_working_day_or_not() throws Throwable {
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        }

        @Then("^Return The Day after checking$")
        public void return_the_day_after_checking() throws Throwable {
            if (dayOfWeek == 1 || dayOfWeek == 6)
                System.out.println("Holiday");

            else
                System.out.println("Working day");


        }
    //4
    String MonthYear;
    LocalDate givenDate;
    String rDate;
    DatePicker dp;
    int Date;

    @Given("^Accept (.+) in YYYY/MM/DD format$")
    public void accept_in_yyyymmdd_format(String date) throws Throwable {
        System.out.println("select current / future date in date picker element");
        driver = initializerDriver();
        rDate = date;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        givenDate = LocalDate.parse(rDate);
        Date = givenDate.getDayOfMonth();
        MonthYear = String.valueOf(givenDate.getMonth()).concat(" ").concat(String.valueOf(givenDate.getYear()));

    }

    @When("^check if given date is future date or current date$")
    public void check_if_given_date_is_future_date_or_current_date() throws Throwable {
        boolean tday = LocalDate.parse(rDate).isBefore(LocalDate.now());
        Assert.assertFalse(tday);
    }

    @And("^Navigate to (.+)$")
    public void navigate_to(String url) throws Throwable {
        driver.get(url);
    }

    @Then("^Switching to Frame$")
    public void switching_to_frame() throws Throwable {
        dp = new DatePicker(driver);
        driver.switchTo().frame(dp.getFrame());
        dp.getDatePicker().click();
    }

    @And("^Selecting the given month$")
    public void selecting_the_given_month() throws Throwable {
        while (true) {
            if (dp.getTitle().getText().equalsIgnoreCase(MonthYear))
                break;
            else
                dp.getNavigation().click();
        }
    }

    @And("^selecting date$")
    public void selecting_date() throws Throwable {
        int count = dp.getDates().size();
        for (int i = 0; i < count; i++) {
            if (dp.getDates().get(i).getText().equalsIgnoreCase(String.valueOf(Date))) {
                dp.getDates().get(i).click();
            }
        }
        System.out.println();

    }
    //5

    int weeks;
    long start;
    long end;
    long randomEpochDay;
    LocalDate d;
    @Given("^Take (.+) as input$")
    public void take_as_input(String range) throws Throwable {
        System.out.println("generate any random future date within specified range of weeks.");
        weeks = Integer.parseInt(range);
    }
    @When("^Generate Random Date with in given Range$")
    public void generate_random_date_with_in_given_range() throws Throwable {
        LocalDate startDate = LocalDate.now().plusDays(1);
        start = startDate.toEpochDay();
        LocalDate endDate = startDate.plusWeeks(weeks);
        end = endDate.toEpochDay();
        randomEpochDay= ThreadLocalRandom.current().longs(start,end).findAny().getAsLong();
    }
    @Then("^Return The Random$")
    public void return_the_random() throws Throwable {
        System.out.println("Randome Date with in "+weeks+" weeks: "+LocalDate.ofEpochDay(randomEpochDay));
        System.out.println("");
    }
    //6
    @Given("^Accept (.+) as input$")
    public void accept_as_input(String range) throws Throwable {
        System.out.println("generate any random future date of week day within specified range of weeks o");
        weeks=Integer.parseInt(range);
        LocalDate startDate=LocalDate.now().plusDays(1);
        start=startDate.toEpochDay();
        LocalDate endDate=startDate.plusWeeks(weeks);
        end=endDate.toEpochDay();
    }

    @When("^Generate  Date with in given Range$")
    public void generate_date_with_in_given_range() throws Throwable {

        RandomDateGenerator rd=new RandomDateGenerator();
        while (true) {
            d = rd.Dategeneration(start, end);
            java.util.Date Rdate = java.util.Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
            int Rday = rd.getDayNumber(Rdate);
            if (Rday != 1 && Rday != 7) {
                break;
            }
        }
    }

    @Then("^Return The RandomDate$")
    public void return_the_randomdate() throws Throwable {
        System.out.println(d+" "+d.getDayOfWeek());
        System.out.println("");
    }
    //7
    int range;
    @Given("^Give (.+) as input$")
    public void give_as_input(String n) throws Throwable {
        System.out.println("print first given N numbers in console. If the number is multiple of 5, it should print fizz. If the number is multiple of 7, it should print buzz. If the number is mutliple of both 5 and 7, it should print fizzbuzz.");
        range=Integer.parseInt(n);
    }
    // ArrayList<String> values = new ArrayList<>();

    @When("^printing Fizz or Buzz accordingly that if the number is multiple of 5 or 7$")
    public void printing_fizz_or_buzz_accordingly_that_if_the_number_is_multiple_of_5_or_7() throws Throwable {
        for(int i=0;i<range;i++)
        {
            if(i % 35==0)
            {
                System.out.println("FizzBuzz"+"");
            }
            else if(i % 5==0)
            {
                System.out.println("Fizz"+"");
            }

            else if(i % 7 == 0)
            {
                System.out.println("Buzz"+"");
            }
            else
            {
                System.out.println(i+"");
            }

        }
       }


    @Then("^print the values to the console$")
    public void print_the_values_to_the_console() throws Throwable {
        System.out.println("success");
    }
}



