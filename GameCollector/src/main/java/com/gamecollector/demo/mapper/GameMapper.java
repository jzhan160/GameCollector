package com.gamecollector.demo.mapper;

import com.gamecollector.demo.model.Game;
import com.gamecollector.demo.model.GameExample;
import java.util.List;

import com.gamecollector.demo.model.ViewerResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface GameMapper {
    int countByExample(GameExample example);

    int deleteByExample(GameExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Game record);

    int insertSelective(Game record);

    List<Game> selectByExample(GameExample example);

    List<ViewerResult> selectViewersInATimeRangeByExample(GameExample example);

    List<ViewerResult> selectViewersOfOneGameByExample(GameExample example);

    Game selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Game record, @Param("example") GameExample example);

    int updateByExample(@Param("record") Game record, @Param("example") GameExample example);

    int updateByPrimaryKeySelective(Game record);

    int updateByPrimaryKey(Game record);


}