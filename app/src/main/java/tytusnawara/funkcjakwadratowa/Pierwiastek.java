package tytusnawara.funkcjakwadratowa;

public class Pierwiastek {
    private long przedPierwiastkiem;
    private long podPierwiastkiem;

    //private Znak znak;

    //public enum Znak
    //{
    //    DODATNI, UJEMNY, ZERO;
    //}

    public Pierwiastek(long przed, long pod/*, Znak z*/)
    {
        przedPierwiastkiem = przed;
        podPierwiastkiem = pod;
        //this.znak = z;
        wylaczCzynnik();

    }

    private void wylaczCzynnik()
    {
        long i = 2;

        while (i <= Math.sqrt(podPierwiastkiem))
        {
            if((podPierwiastkiem % (i*i)) == 0)
            {
                przedPierwiastkiem *= i;
                podPierwiastkiem /= (i*i);
            }
            else
            {
                i++;
            }
        }
        //System.out.println(przedPierwiastkiem + " pierwiastkow z " +
        //podPierwiastkiem);


    }



    public long getPrzedPierwiastkiem()
    {
        return przedPierwiastkiem;
    }

    public void podzielPrzedPierwistkiem(long dzielnik)
    {
        if (dzielnik !=0)
        {
            this.przedPierwiastkiem /= dzielnik;
        }

    }

    public long getPodPierwiastkiem()
    {
        return podPierwiastkiem;
    }

    /*public Znak getZnak()
    {
        return znak;
    }*/

    public void zmienNaprzeciwny()
    {
        this.przedPierwiastkiem *= -1;
        /*if(this.znak == Znak.DODATNI)
        {
            this.znak = Znak.UJEMNY;
            System.out.println("zmiana dodatnni na ujemny");
        }

        if (this.znak == Znak.UJEMNY)
        {
            this.znak = Znak.DODATNI;
            System.out.println("zmiana ujemny na dodatni");
        }
        else {
            System.out.println("znak = 0");
        }*/

    }
    public void wyzerujPierwiastek()
    {
        this.przedPierwiastkiem = 0;
    }
}
