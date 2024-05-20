def partition(array, low, high):
    pivot = array[high][0]
 
    i = low - 1
    for j in range(low, high):
        if array[j][0] <= pivot:
            i = i + 1
            temp = array[i]
            array[i] = array[j]
            array[j] = temp
    temp = array[i + 1]
    array[i + 1] = array[high]
    array[high] = temp
    return i + 1

def quickSort(array, low, high):
    if low < high:
        p = partition(array, low, high)
        quickSort(array, low, p - 1)
        quickSort(array, p + 1, high)


n, k = input().split()
n = int(n)
k = int(k)
tupleList=[]
for i in range(n):
    need, gain = input().split()
    need = int(need)
    gain = int(gain)
    if(need < gain):
        tupleList.append((need,gain))
s = len(tupleList)
quickSort(tupleList,0,s-1)
for tuple in tupleList:
    if tuple[0] > k:
        break
    k += tuple[1] - tuple[0]
print(k)
