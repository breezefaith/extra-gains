class max_heap(object):
    """Binary max-heap

    Supports most standard heap operations (insert, peek, extract_max).
    Can be used for building a priority queue or heapsort. Since Python
    doesn't have built-in arrays, the underlying implementation uses a
    Python list instead. When initialized, max_heap creates a new list of
    fixed size or uses an existing list.
    """

    def __init__(self, size=20, data=None):
        """Initialize a binary max-heap.

        size: Total capacity of the heap.
        data: List containing the desired heap contents. 
              The list is used in-place, not copied, so its contents 
              will be modified by heap operations.
              If data is specified, then the size field is ignored."""

        # Add to this constructor as needed
        if data is not None:
            self.max_size = len(data)
            self.length = len(data)
            self.heap = data
        else:
            self.max_size = size
            self.length = 0
            self.heap = [None] * size

    def get_heap(self):
        return self.heap

    def insert(self, data):
        """Insert an element into the heap.

        Raises IndexError if the heap is full."""
        # Tips : insert 'data' at the end of the list initially
        #      : swap with its parent until the parent is larger or you 
        #      : reach the root
        if self.length == self.max_size:
            raise IndexError
        self.heap[self.length] = data
        i = self.length
        while i != 0 and self.heap[i] > self.heap[self.__get_parent(i)]:
            temp = self.__get_parent(i)
            self.__swap(i, temp)
            i = temp

        self.length = self.length + 1

    def peek(self):
        """Return the maximum value in the heap."""
        return self.heap[0]

    def extract_max(self):
        """Remove and return the maximum value in the heap.

        Raises KeyError if the heap is empty."""
        # Tips: Maximum element is first element of the list
        #     : swap first element with the last element of the list.
        #     : Remove that last element from the list and return it.
        #     : call __heapify to fix the heap
        if self.length == 0:
            raise KeyError
        max = self.heap[0]
        self.__swap(0, self.length - 1)
        del self.heap[self.length - 1]
        self.length = self.length - 1
        self.__heapify(0, self.length)
        return max

    def __heapify(self, curr_index, list_length=None):
        # helper function for moving elements down in the heap
        # Page 157 of CLRS book
        if list_length is None:
            list_length = self.length
        t = None
        while t is None:
            t = curr_index
            left = self.__get_left(curr_index)
            right = self.__get_right(curr_index)
            if left < list_length and self.heap[curr_index] < self.heap[left]:
                t = left

            if right < list_length and self.heap[t] < self.heap[right]:
                t = right

            if t != curr_index:
                self.__swap(curr_index, t)
                curr_index = t
                t = None
        pass

    def build_heap(self):
        # builds max heap from the list l.
        # Tip: call __heapify() to build to the list
        #    : Page 157 of CLRS book
        i = self.length // 2 - 1
        while i >= 0:
            self.__heapify(i, self.length)
            i = i - 1
        pass

    ''' Optional helper methods may be used if required '''
    ''' You may create your own helper methods as required.'''
    ''' But do not modify the function definitions of any of the above methods'''

    def __get_parent(self, loc):
        # left child has odd location index
        # right child has even location index
        # if loc % 2 == 0:
        #     parent = int((loc - 2) / 2)
        # else:
        parent = int((loc - 1) / 2)
        return parent

    def __get_left(self, loc):
        return 2 * loc + 1

    def __get_right(self, loc):
        return 2 * loc + 2

    def __swap(self, a, b):
        # swap elements located at indexes a and b of the heap
        temp = self.heap[a]
        self.heap[a] = self.heap[b]
        self.heap[b] = temp


def heap_sort(l):
    """Sort a list in place using heapsort."""
    # Note: the heap sort function is outside the class
    #     : The sorted list should be in ascending order
    # Tips: Initialize a heap using the provided list
    #     : Use build_heap() to turn the list into a valid heap
    #     : Repeatedly extract the maximum and place it at the end of the list
    #     : Refer page 161 in the CLRS textbook for the exact procedure
    size = len(l)
    mheap = max_heap(size, l)
    mheap.build_heap()

    while mheap.length > 0:
        l.append(mheap.extract_max())

    for i in range(0, size//2):
        temp = l[size - 1 - i]
        l[size - 1 - i] = l[i]
        l[i] = temp

    return l

