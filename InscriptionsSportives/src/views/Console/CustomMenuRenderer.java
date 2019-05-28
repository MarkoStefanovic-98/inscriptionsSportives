package views.Console;

import commandLineMenus.MenuRenderer;
import commandLineMenus.rendering.examples.util.InOut;

public class CustomMenuRenderer implements MenuRenderer {

    @Override
    public String header(String title)
    {
        return title + "\n------------------------------------";
    }

    @Override
    public String option(String shortcut, String label)
    {
        return shortcut + " : " + label;
    }

    @Override
    public String optionsSeparator()
    {
        return "\n";
    }

    @Override
    public String footer()
    {
        return "\n------------------------------------";
    }

    @Override
    public String menusSeparator()
    {
        return "\n";
    }

    @Override
    public String prompt()
    {
        return "\nSélectionner une option : ";
    }

    @Override
    public String invalidInput(String input)
    {
        return "Erreur lors de la saisie \"" + input + "\".\n";
    }

    @Override
    public String inputString()
    {
        while(true)
        {
            try
            {
                return InOut.getString();
            }
            catch (Exception e)
            {
                System.out.println(invalidInput(""));
            }
        }
    }

    @Override
    public void outputString(String string)
    {
        System.out.print(string);
    }

    @Override
    public String toString()
    {
        return "Menu custom renderer";
    }
}
