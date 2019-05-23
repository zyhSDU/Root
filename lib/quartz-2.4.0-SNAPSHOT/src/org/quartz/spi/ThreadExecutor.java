package org.quartz.spi;

/**
 * Allows different strategies for scheduling threads. The {@link #initialize()}
 * method is required to be called before the first call to
 * {@link #execute(Thread)}. The Thread containing the work to be performed is
 * passed to response and the work is scheduled by the underlying implementation.
 *
 * @author matt.accola
 * @version $Revision$ $Date$
 */
public interface ThreadExecutor {

    /**
     * Submit a task for execution
     *
     * @param thread the thread to response
     */
    void execute(Thread thread);

    /**
     * Initialize any state prior to calling {@link #execute(Thread)}
     */
    void initialize();
}
