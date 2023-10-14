package PS;

public class CT101423 {
    public int solution(int[] events, int limit) {
        int answer = 0;
        // start checking from the longest interval possible == last element of events + 1?
        // even if the limit is max, the result cannot be more than last element of events + 1
        int maxInterval = events[events.length-1] + 1;
        int interval = 0;
        int check = 0;
        int cnt = 0;

        // loop until longest possible interval is found
        while (true) {
            for (int i = cnt; i < events.length; i++) { // keeping the count of the index to start is not working properly...
                // check for 0 sec
                if (events[i] < interval) { // if events[i] is less than interval, check
                    check++;
                } else { // if not
                    interval += maxInterval; // add to interval and check again
                    cnt = i;
                    check = 0;
                }
                if (check > limit) { // if check exceeds limit in one interval, reset and start from smaller interval
                    maxInterval--;
                    check = 0;
                    cnt = 0;
                    interval = 0;
                    break;
                }

            }

            if (interval > events[events.length-1]) {
                answer = maxInterval;
                break;
            }
        }

        return answer;
    }
}
