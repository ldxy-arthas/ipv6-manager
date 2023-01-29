package manager.domain.system.model.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 *@author yuluo
 *@createTime 2023-01-29  18:57
 * ipv6 收集日志 此日志不允许删除，只能从数据库删除，避免数据遗失
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "t_log")
public class TCollectionLog {

	@MongoId
	private String cId;

	/**
	 * 收集id
	 */
	private String id;

	@CreatedDate
	@JsonFormat(locale = "zh", timezone = "Asia/Shanghai", shape = JsonFormat.Shape.STRING)
	private Date collectiontime;

	private String status;

	private String collectionIp;

	private String content;

}
