//package com.fancode.utils;
//
//import com.fancode.config.Constants;
//
//public class TestHelper {
//
//	/**
//	 * This method Check for the latitude and longitude between values specified in Constant
//	 * @param latitude
//	 * @param longitude
//	 * @return
//	 */
//	public static boolean LatituteLongitudeCheckerForUser(double latitude, double longitude) 
//	{
//		if (latitude >= Constants.LAT_START && latitude <= Constants.LAT_END && longitude >= Constants.LNG_START
//				&& longitude <= Constants.LNG_END) 
//		{
//			return true;
//		}
//		return false;
//	}
//	
//	
//	/**
//	 * This method calculates the percentage
//	 * @param completedTask
//	 * @param totalTask
//	 * @return
//	 */
//	public static double CalculateTaskCompletedPercentage (double completedTask, double totalTask) {
//		return (completedTask/totalTask) * 100;
//	}
//}


package com.fancode.utils;

import com.fancode.model.Todo;

public class CityHelper {
    public static boolean isInFanCodeCity(double lat, double lng) {
        return (lat >= -40 && lat <= 5) && (lng >= 5 && lng <= 100);
    }

//    public static double calculateCompletionPercentage(int completedTasks, int totalTasks) {
//        return ((double) completedTasks / totalTasks) * 100;
//    }
    
 // Calculate the completion percentage of tasks
    public static double calculateCompletionPercentage(Todo[] todos) {
        int totalTasks = todos.length;
        int completedTasks = 0;

        for (Todo todo : todos) {
            if (todo.isCompleted()) {
                completedTasks++;
            }
        }
        return (totalTasks == 0) ? 0 : ((double) completedTasks / totalTasks) * 100;
    }
}
