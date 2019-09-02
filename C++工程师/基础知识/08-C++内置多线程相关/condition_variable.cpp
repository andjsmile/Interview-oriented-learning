
// condition_variable is used in combination with a std::mutex to facilitate inter-thread communication.
// 促进线程通信
#include <iostream>
#include <string>
#include <thread>
#include <mutex>
#include <condition_variable>

std::mutex m;
std::condition_variable cv;
std::string data;
bool ready=false;
bool processed=false;


void worker_thread()
{
    //wait until main() sends data
    std::unique_lock<std::mutex> lk(m);
    //blocks the current thread until the condition variable is woken up 
    cv.wait(lk,[]{return ready==true;});

    //after the wait,we own the lock
    std::cout<<"worker thread is processing data"<<std::endl;
    data+="after processing";

    // send data back to main
    processed=true;
    std::cout<<"Worker thread signals data processing completed"<<std::endl;

    lk.unlock();
    cv.notify_one();
}

int main()
{
    std::thread worker(worker_thread);

    // send data to worker thread
    data="Example data";
    {
        std::lock_guard<std::mutex> lk(m);
        ready=true;
        std::cout<<"main() signal data ready for processing"<<std::endl;

    }
    cv.notify_one();

    // wait for the worker
    {
        std::unique_lock<std::mutex> lk(m);
        cv.wait(lk,[]{return processed;});
    }
    std::cout<<"Back int main(),data="<<data<<std::endl;
    worker.join();
}

