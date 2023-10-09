package com.imwyf.entity;

import com.imwyf.sample.FixedSample;
import com.imwyf.sample.Sample;
import com.imwyf.text.IndexCiphertext;
import com.imwyf.text.SearchTrapdoor;
import com.imwyf.text.transportable.TransportableFinalCiphertext;
import com.imwyf.text.transportable.TransportableIndexCiphertext;
import com.imwyf.util.ComputeUtils;
import com.imwyf.util.DatabaseUtils;
import com.imwyf.util.TCPUtils;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @BelongsProject: Abe_Se
 * @BelongsPackage: com.imwyf.entity
 * @Author: imwyf
 * @Date: 2023/5/10 10:43
 * @Description: 云存储服务器
 */
public class CloudServer extends AbstractEntity {
    public DatabaseUtils databaseUtils = new DatabaseUtils();
    private final Map<IndexCiphertext, TransportableFinalCiphertext> storeMap = new ConcurrentHashMap<>();
    public TCPUtils tcp = new TCPUtils(); // tcp通信模块

    /**
     * 存储数据使用者发来的索引和密文
     */
    public void store(TransportableIndexCiphertext transportableIndexCiphertext, TransportableFinalCiphertext transportableFinalCiphertext) {
        IndexCiphertext indexCiphertext = IndexCiphertext.reBuild(transportableIndexCiphertext,getPublicParams());
        storeMap.put(indexCiphertext, transportableFinalCiphertext);
    }

    /**
     * 根据搜索陷门看是否存在匹配，如果有匹配上的返回对应可传输密文，否则返回null
     * @param searchTrapdoor
     * @return
     */
    public TransportableFinalCiphertext checkSearchTrapdoor(SearchTrapdoor searchTrapdoor) {
        for (Map.Entry<IndexCiphertext, TransportableFinalCiphertext> entry : storeMap.entrySet()) {
            IndexCiphertext indexCiphertext = entry.getKey();
            TransportableFinalCiphertext transportableFinalCiphertext = entry.getValue();
            if (checkSearchTrapdoor(searchTrapdoor, indexCiphertext)) {
                return transportableFinalCiphertext;
            }
        }
        return null;
    }

    /**
     * 云服务检查是否存在匹配的关键字
     *
     * @param searchTrapdoor  搜索陷门
     * @param indexCiphertext 密态关键字索引
     */
    private boolean checkSearchTrapdoor(SearchTrapdoor searchTrapdoor, IndexCiphertext indexCiphertext) {
        Pairing pairing = getPublicParams().getCurveElementParams().getPairing();
        Element T1 = searchTrapdoor.getT1();
        Element T2 = searchTrapdoor.getT2();
        Element T3 = searchTrapdoor.getT3();
        Element T4 = searchTrapdoor.getT4();
        int keywordNumber = searchTrapdoor.getKeywordNumber();

        Element W1 = indexCiphertext.getW1();
        Element W2 = indexCiphertext.getW2();
        Element W3 = indexCiphertext.getW3();
        List<Element> W_list = indexCiphertext.getW_list();

        Element left_1 = pairing.pairing(T2, W2).getImmutable();
        Element right_1 = pairing.pairing(T1, W1).getImmutable();
        Element right_2 = pairing.pairing(T3, W3).getImmutable();
        Element right = right_1.mul(right_2).getImmutable();

        Sample<Element> sample = new FixedSample<>(W_list, keywordNumber);
        while (sample.hasNext()) {
            List<Element> keyWordSampleSet = sample.next();
            Element mulResult = ComputeUtils.mul(keyWordSampleSet);
            Element left_2 = pairing.pairing(mulResult, T4).getImmutable();
            Element left = left_2.mul(left_1).getImmutable();
            if (left.equals(right)) {
                return true;
            }
        }
        return false;
    }


}
