/**
 * 
 */
package com.shanlin.demo.lock;

import java.util.concurrent.Semaphore;

/**
 * @author 13073457
 *
 */
public class SemaphoreT {
    private static final int MAX_AVAILABLE = 5;
    class Pool {
        private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

        public Object getItem() throws InterruptedException {
            available.tryAcquire();
            Object object = getNextAvailableItem();
            available.release();
            return object;
        }

        public void putItem(Object x) {
            if (markAsUnused(x))
                available.release();
        }

        protected Object[] items = new Object[] {0,1,2,3,4};
        protected boolean[] used = new boolean[MAX_AVAILABLE];

        protected synchronized Object getNextAvailableItem() {
            for (int i = 0; i < MAX_AVAILABLE; ++i) {
                if (!used[i]) {
                    used[i] = true;
                    return items[i];
                }
            }
            return null; // not reached
        }

        protected synchronized boolean markAsUnused(Object item) {
            for (int i = 0; i < MAX_AVAILABLE; ++i) {
                if (item == items[i]) {
                    if (used[i]) {
                        used[i] = false;
                        return true;
                    } else
                        return false;
                }
            }
            return false;
        }
    }
    
    public static void main(String[] args) {
        SemaphoreT t = new SemaphoreT();
        Pool pool = t.new Pool();
        
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(pool.getItem());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
