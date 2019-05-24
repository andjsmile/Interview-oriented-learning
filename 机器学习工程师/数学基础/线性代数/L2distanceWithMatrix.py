###################
## 计算矩阵的距离  ##
###################

import numpy as np

# 1.使用循环的方法来实现

def computeDistanceWithLoop(X,Y):
    """
    input: X,Y two numpy array
    X-500x3072
    Y-5000x3072
    return the euclidean distance between two array
    """
    num_x=X.shape[0]                    # 500
    num_y=Y.shape[0]                    # 5000
    dists=np.zeros((num_x,num_y))       # 500x5000
    for i in range(num_x):
        distance=np.sqrt(np.sum(np.square(Y-X[i,:]),axis=1))   # 5000x1
        dists[i,:]=distance             # 
    return dists


# 2.使用矩阵方法来算
def computeDistanceNoLoop(X,Y):
    """
    input: X,Y two numpy array
    X-500x3072
    Y-5000x3072
    return euclidean distacn array
    """
    num_x=X.shape[0]                    # 500
    num_y=Y.shape[0]                    # 5000
    dists=np.zeros((num_x,num_y))       # 500x5000
    M=np.dot(X,Y.T)                     # 500x5000
    nrow=M.shape[0]                     # 500
    ncol=M.shape[0]                     # 5000
    tx=np.diag(np.dot(X,X.T))           # 对角线元素  500x1      向量的模平方
    ty=np.diag(np.dot(Y,Y.T))           #           5000x1
    tx=np.reshape(np.repeat(tx,ncol),M.shape)      # 500x5000  拓展向量
    ty=np.reshape(np.repeat(ty,nrow),M.T.shape)    # 5000x500
    sq=-2*M+tx+ty.T           
    dists=np.sqrt(sq)

    return dists

    

