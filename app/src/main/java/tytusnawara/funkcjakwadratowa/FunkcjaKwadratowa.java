package tytusnawara.funkcjakwadratowa;

import android.os.AsyncTask;

public class FunkcjaKwadratowa{
    private long a, b, c;
    private XPierwiastek x1, x2;

    private Pierwiastek pZDelty, ujemnyPZDelty;
    private long delta;

    static int iloscMiejscZerowych;

    static String[] sObliczenia = new String[17];

    static int liczbaLinijek = 0;
    static boolean isFunkcjaKwadratowa = true;

    public FunkcjaKwadratowa(int fA, int fB, int fC)
    {
        this.a = fA;
        this.b = fB;
        this.c = fC;
        liczbaLinijek = 0;

        clear_sObliczenia();
        liczMiejscaZerowe();
    }

    public FunkcjaKwadratowa(double fA, double fB, double fC)
    {
        int i = 10;
        liczbaLinijek = 0;


        clear_sObliczenia();

        while (true)
        {
            if ((fA * i)% 1 == 0 &&
                    (fB * i)% 1 == 0 &&
                    (fC * i)% 1 == 0 )
            {
                break;
            }
            else
            {
                i *= 10;
            }
        }

        String bZnak;
        String cZnak;

        if (fB >= 0)
        {
            bZnak = "+";
        }

        else
        {
            bZnak = "-";
        }

        if (fC >= 0)
        {
            cZnak = "+";
        }

        else
        {
            cZnak = "-";
        }

        sObliczenia[liczbaLinijek] = "`"+fA+"`"+ "`x^2" +bZnak+"`" +
                "`"+Math.abs(fB)+"`"+   "`x "+cZnak+"`"+ "`"+Math.abs(fC)+"=0`"
        +"`"+ "// **"+i+"`";

        liczbaLinijek++;

        this.a = (long)(fA*i);
        this.b = (long)(fB*i);
        this.c = (long)(fC*i);
        liczMiejscaZerowe();
    }

    private void liczMiejscaZerowe()
    {

        //liczbaLinijek = 0;
        //trzeba dodac minusy

        String bZnak;
        String cZnak;

        if (b >= 0)
        {
            bZnak = "+";
        }

        else
        {
            bZnak = "-";
        }

        if (c >= 0)
        {
            cZnak = "+";
        }

        else
        {
            cZnak = "-";
        }

        sObliczenia[liczbaLinijek] = "`"+a+"`"+ "`x^2" +bZnak+"`" +
                "`"+Math.abs(b)+"`"+   "`x "+cZnak+"`"+ "`"+Math.abs(c)+"=0`";

        liczbaLinijek++;

        sObliczenia[liczbaLinijek] = "`Delta = b^2 - 4ac`";

        liczbaLinijek++;

        sObliczenia[liczbaLinijek] ="`"+ "a=" +a+"text( ) b="+b+
                "text( ) c="+c+ "`";

        liczbaLinijek++;

        String ac4Znak = "-";//a*c>0

        if(a*c <= 0)
        {
            ac4Znak = "+";
        }

        String bKwadrat = "(" + Long.toString(b) + ")";

        if (b >= 0)
        {
            bKwadrat = Long.toString(b);
        }


        sObliczenia[liczbaLinijek] = "`Delta =`" +
                "`" +bKwadrat +"^2`"+ "`"+ac4Znak+
                 Long.toString(Math.abs(-4*a*c))+ "`";

        liczbaLinijek++;

        delta = (b*b) - 4*(a*c);

        sObliczenia[liczbaLinijek] = "`Delta =`"+ "`" + delta+ "`";

        liczbaLinijek++;


        if (delta < 0)
        {
            this.iloscMiejscZerowych = 0;

            sObliczenia[liczbaLinijek] = "`Delta < 0`";

            liczbaLinijek++;

        }

        else if(delta == 0)
        {


            //tutaj trzeba dać jedno miejsce zerowe na środku
            pZDelty = new Pierwiastek(0, delta);
            this.iloscMiejscZerowych = 1;
            this.x1 = new XPierwiastek(pZDelty, (-this.b), 2*a);

            //System.out.println("Delta równa 0");
            //x1.wypiszWynik();
        }

        else
        {
            sObliczenia[liczbaLinijek] = "`Delta > 0`";

            liczbaLinijek++;

            pZDelty = new Pierwiastek(1, delta);
            ujemnyPZDelty = new Pierwiastek(-1, delta);
            this.iloscMiejscZerowych = 2;
            this.x1 = new XPierwiastek(pZDelty, (-this.b), 2*a);
            //pZDelty.zmienNaprzeciwny();

            this.x2 = new XPierwiastek(ujemnyPZDelty, (-this.b), 2*a);
            //System.out.println("Delta wieksza od 0");
            //x1.wypiszWynik();
            //x2.wypiszWynik();
        }
    }

    static void iniciate_sObliczenia(int int_liczbaLinijek)
    {
        liczbaLinijek = int_liczbaLinijek;
        sObliczenia = new String[int_liczbaLinijek];
    }

    static void clear_sObliczenia()
    {
        for (int i = 0; i < sObliczenia.length; i++)
        {
            sObliczenia[i] = "";
        }
    }

    public String getX1()
    {
        return x1.toString();
    }

    public String getX2()
    {
        return x2.toString();
    }
}
