package tytusnawara.funkcjakwadratowa;

public class XPierwiastek {
    //licznik
    long licznik;
    Pierwiastek pierwiastek;

    //mianownik
    long mianownik;

    //typ pierwiastka
    private XPierwiastekType type;

    String sx = "";

    @Override public String toString()
    {
        if (type == XPierwiastekType.PROSTY_INT)
        {
            sx = "`"+ licznik+"`";
        }

        if (type == XPierwiastekType.LICZNIK_I_MIANOWNIK)
        {
            if(mianownik == 1)
            {
                sx = "`"+ licznik +"`";
            }

            else
            {
                sx = "`"+ licznik+" / " +mianownik+ "`";
            }

        }
        else
        {

            String smianownik = "/" + Long.toString(mianownik);



            String przedPierwiastkiem =
                    Long.toString(pierwiastek.getPrzedPierwiastkiem());

            if (pierwiastek.getPrzedPierwiastkiem() >= 0)
            {
                przedPierwiastkiem =
                        "+" + Long.toString(pierwiastek.getPrzedPierwiastkiem());

            }

            String lewyNawias = "(";
            String prawyNawias = ")";

            if (pierwiastek.getPrzedPierwiastkiem() == 1)
            {
                przedPierwiastkiem = "+";
            }

            else if(pierwiastek.getPrzedPierwiastkiem() == -1)
            {
                przedPierwiastkiem = "-";
            }

            if (mianownik == 1)
            {
                smianownik = "";
                prawyNawias = "";
                lewyNawias = "";
            }

                sx = "`"+ lewyNawias +licznik+przedPierwiastkiem
                        + "sqrt "+pierwiastek.getPodPierwiastkiem()+
                        prawyNawias + mianownik + "`";

                sx = "`"+ lewyNawias+ "56"  +"-23" +"sqrt "+"34" +prawyNawias + smianownik +"`";



        }
        return sx;
    }

    public XPierwiastek(Pierwiastek pDelta, long i_licznik,
                        long i_mianownik)
    {
        this.pierwiastek = pDelta;
        this.licznik = i_licznik;
        this.mianownik = i_mianownik;

        //liczba jest wymierna
        if (pDelta.getPrzedPierwiastkiem() == 0 ||
            pDelta.getPodPierwiastkiem() == 0)
    {
        type = XPierwiastekType.LICZNIK_I_MIANOWNIK;

        if (licznik % mianownik == 0)
        {
            type = XPierwiastekType.PROSTY_INT;
            licznik /= mianownik;
            mianownik = 1;
        }

    }

        else if(pDelta.getPodPierwiastkiem() == 1)
        {
            type = XPierwiastekType.LICZNIK_I_MIANOWNIK;

                licznik += pDelta.getPrzedPierwiastkiem();

            //potrzeba skracania
            pierwiastek.wyzerujPierwiastek();

            if (licznik % mianownik == 0)
            {
                type = XPierwiastekType.PROSTY_INT;
                licznik /= mianownik;
                mianownik = 1;


            }

            //skracanie ułamka
            else
            {
                //System.out.println("ulamek ktory mozna skruicic");


            }


        }

        else //rozwiązanie niewymierne
        {
            type = XPierwiastekType.ZLOZONY;
            //System.out.println("rozwiazanie niewymierne");

        }
        skrocUlamek();

    }



    private void skrocUlamek()
    {


        if (type == XPierwiastekType.ZLOZONY)
        {
            //System.out.println("skaracanie zlozonego ulamka");

            int i = 2;
            while (i <= Math.abs(mianownik))
            {
                if(licznik % i == 0 &&
                        mianownik % i == 0
                        && this.pierwiastek.getPrzedPierwiastkiem()
                        % i == 0)
                {
                    licznik /= i;
                    mianownik /= i;
                    pierwiastek.podzielPrzedPierwistkiem(i);
                    //System.out.println("skracanie powiodlo sie");

                }

                else
                {
                    i++;
                }
            }
        }
        else if (type == XPierwiastekType.LICZNIK_I_MIANOWNIK)
        {
            //System.out.println("skracanie normalnego ulamka");

            int i = 2;
            while (i < Math.abs(mianownik) && i <= Math.abs(licznik))
            {
                if(licznik % i == 0 &&
                        mianownik % i == 0)
                {
                    licznik /= i;
                    mianownik /= i;
                }

                else
                {
                    i++;
                }
            }
        }

        else {
            //System.out.println("error");
        }

    }

    //eut
    /*public void wypiszWynik()
    {
        if(type == XPierwiastekType.PROSTY_INT)
        {
            System.out.println("Pierwiastek to po prostu: " + licznik);
        }

        if (type == XPierwiastekType.LICZNIK_I_MIANOWNIK)
        {
            System.out.println("Pierwiastek to: " + licznik + " / " + mianownik);
        }

        if (type == XPierwiastekType.ZLOZONY)
        {
            if (pierwiastek.getPrzedPierwiastkiem() <0)
            {
                System.out.println(licznik + " " +
                        pierwiastek.getPrzedPierwiastkiem()
                        +" pierwiastkow z " + pierwiastek.getPodPierwiastkiem()
                +" / " + mianownik);
            }
            else
            {
                System.out.println(licznik + " + " +
                        pierwiastek.getPrzedPierwiastkiem()
                        +" pierwiastkow z " + pierwiastek.getPodPierwiastkiem()
                        +" / " + mianownik);
            }
        }
    }*/
}
