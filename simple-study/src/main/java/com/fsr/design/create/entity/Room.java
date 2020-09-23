package com.fsr.design.create.entity;

/**
 * Created by Room
 *
 * @author Aokai.Fu
 * @version v1.0
 * @apiNote Room
 * @create 2020/7/28 14:20
 */
public class Room implements MapSite {
    private Integer roomNo;
    private MapSite[] mapSites;

    public MapSite getSide(Direction direction) {
        return null;
    }

    public void setSide(Direction direction, MapSite mapSite) {

    }

    @Override
    public void enter() {

    }


}
