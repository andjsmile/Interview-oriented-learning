#include <unistd.h>
#include <sys/mman.h>
void *mmap(void *start,size_t length,int prot,int flags,int fd,off_t offset);

// returns: pointer to mapped area if ok        
//          MAP_FAILED(-1)  error 
// mapped area  : memory-mapped region for shared libraries


