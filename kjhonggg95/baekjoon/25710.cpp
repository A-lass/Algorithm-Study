#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int n, ans;
int freq[2000];
bool used[1000000];

int main()
{
    fastio;
    cin >> n;
    vector<int> v(n), nums;
    for (int i = 0; i < n; i++)
    {
        cin >> v[i];
        freq[v[i]]++;
    }

    for (int i = 1; i <= 999; i++)
    {
        if (freq[i] > 0)
            nums.push_back(i);

        if (freq[i] >= 2)
            nums.push_back(i);
    }

    for (int i = 0; i < nums.size() - 1; i++)
    {
        for (int j = i + 1; j < nums.size(); j++)
        {
            int sum = nums[i] * nums[j];
            
            if (used[sum]) continue;
            used[sum] = true;

            int score = 0;

            while (sum)
            {
                score += sum % 10;
                sum /= 10;
            }

            ans = max(ans, score);
        }
    }

    cout << ans << '\n';
}