package es.uji.apps.cryptoapplet.ui.service.commands;

import es.uji.apps.cryptoapplet.crypto.BrowserType;
import es.uji.apps.cryptoapplet.keystore.KeyStoreManager;
import es.uji.apps.cryptoapplet.ui.service.DataObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;

public class CertificateListCommand implements ServiceCommand
{
    private KeyStoreManager keyStoreManager;

    public CertificateListCommand() throws GeneralSecurityException, IOException
    {
        keyStoreManager = new KeyStoreManager(BrowserType.FIREFOX);
    }

    public DataObject execute()
    {
        DataObject data = new DataObject();

        int length = keyStoreManager.getCertificates().size();
        int index = 0;

        for (X509Certificate certificate : keyStoreManager.getCertificates())
        {
            DataObject certificateData = new DataObject();
            certificateData.put("dn", certificate.getSubjectDN().toString());
            certificateData.put("serial", certificate.getSerialNumber());

            data.put("certificate", certificateData);
        }

        return data;
    }
}