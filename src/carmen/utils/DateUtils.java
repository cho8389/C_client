package carmen.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javafx.beans.property.SimpleObjectProperty;

public class DateUtils {

	  public static Date asDate(LocalDate localDate) {
		  if(localDate!=null) {
			  return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		  }else {
			  return null;
		  }
	  }

	  public static Date asDate(LocalDateTime localDateTime) {
		  if(localDateTime!=null) {
			  return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		  }else {
			  return null;
		  }
	  }

	  public static LocalDate asLocalDate(Date date) {
		  if(date!=null) {
			  return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		  }else {
			  return null;
		  }
	  }

	  public static LocalDateTime asLocalDateTime(Date date) {
		  if(date!=null) {
			  return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
		  }else {
			  return null;
		  }
	  }
	  public static int sti(String str) {
		  boolean isEmpty = str == null || str.trim().length() == 0;
		  if(!isEmpty) {
			  return Integer.parseInt(str);
		  }else {
			  return 0;
		  }
	  }
	  public static java.sql.Date uts(Date date){
		  if(date!=null) {
			  return new java.sql.Date(date.getTime());
		  }
		  return null;
	  }
	  public static SimpleObjectProperty<java.sql.Date> datenull(java.util.Date date){
		  if(date!=null) {
			  return new SimpleObjectProperty<java.sql.Date>( new java.sql.Date(date.getTime()));
		  }
		  return new SimpleObjectProperty<java.sql.Date>();
	  }
	  public static SimpleObjectProperty<java.sql.Timestamp> timenull(java.util.Date date){
		  if(date!=null) {
			  return new SimpleObjectProperty<java.sql.Timestamp>( new java.sql.Timestamp(date.getTime()));
		  }
		  return new SimpleObjectProperty<java.sql.Timestamp>();
	  }
	  
	}