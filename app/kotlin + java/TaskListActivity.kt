class TaskListActivity : AppCompatActivity() {
    private var recyclerViewTasks: RecyclerView? = null
    private var taskAdapter: TaskAdapter? = null
    private var taskViewModel: TaskViewModel? = null
    @Override
    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)
        recyclerViewTasks = findViewById(R.id.recycler_view_tasks)
        recyclerViewTasks.setLayoutManager(LinearLayoutManager(this))
        recyclerViewTasks.setHasFixedSize(true)
        taskAdapter = TaskAdapter()
        recyclerViewTasks.setAdapter(taskAdapter)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        taskViewModel.getAllTasks().observe(this, object : Observer<List<Task?>?>() {
            @Override
            fun onChanged(tasks: List<Task?>?) {
                taskAdapter.setTasks(tasks)
            }
        })
    }
}
