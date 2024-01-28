from typing import List


class LongestConsecutiveSequence:

    def longestConsecutive(self, nums: List[int]) -> int:

        lookup = {}
        for n in nums:
            lookup[n] = True

        sequences = []
        for n in nums:

            # print(lookup)

            if not lookup.get(n, False):
                continue

            sequence = [n]
            lookup[n] = False

            if lookup.get(n+1, False):
                cur = n+1
                while lookup.get(cur, False):
                    sequence.append(cur)
                    lookup[cur] = False
                    cur += 1

            if lookup.get(n-1, False):
                cur = n-1
                while lookup.get(cur, False):
                    sequence.append(cur)
                    lookup[cur] = False
                    cur -= 1

            print(sequence)
            sequences.append(sequence)

        print(sequences)
        maxValue = 0
        for seq in sequences:
            maxValue = max(maxValue, len(seq))

        return maxValue

if __name__ == '__main__':
    driver = LongestConsecutiveSequence()
    # nums = [100, 4, 200, 1, 3, 2]
    nums = [0,3,7,2,5,8,4,6,0,1]
    ans = driver.longestConsecutive(nums)
    print('Answer', ans)

# def test_base():
#     driver = LongestConsecutiveSequence()
#     nums = [100, 4, 200, 1, 3, 2]
#     ans = driver.longestConsecutive(nums)
#     print(ans)
#
# def test_general():
#     driver = LongestConsecutiveSequence()
#     nums = [0,3,7,2,5,8,4,6,0,1]
#     ans = driver.longestConsecutive(nums)





