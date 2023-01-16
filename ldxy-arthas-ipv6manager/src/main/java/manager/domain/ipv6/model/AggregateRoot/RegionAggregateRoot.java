package manager.domain.ipv6.model.AggregateRoot;


import jakarta.annotation.Resource;
import manager.repository.impl.Ipv6Repository;
 //地区聚合根
public class RegionAggregateRoot {
    @Resource
    Ipv6Repository ipv6Repository;

    private  String id;
    /**
     * 地区名
     */
    private String regionName;

    /**
     * 地区级别名字
     */
    private String regionLevelName;
    /**
     * 地区级别id
     */
    private String regionLevelId;


    /**
     * 地区编号
     */
    private String regionNumber;
    private RegionAggregateRoot(){
    }
    public static class Builder{

    }

    //通过地区名字查询ipv6分配情况
//    public RegionAggregateRoot findRegionAggregateRootByRegionName(String regionName){
//
//    }
//     //通过地区级别查询ipv6分配情况
//     public RegionAggregateRoot findRegionAggregateRootByRegionLevelName(String RegionLevelName){
//
//     }
 }
