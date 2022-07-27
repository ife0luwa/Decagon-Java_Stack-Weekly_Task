package dev.koorius.service;

public class StoreSalesThread extends Thread{
    private StoreSales storeSales;


    public StoreSalesThread(StoreSales storeSales) {
        this.storeSales = storeSales;
    }

    @Override
    public void run() {
            storeSales.sell();

    }
}
